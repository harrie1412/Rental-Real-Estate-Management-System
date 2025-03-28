package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;
import com.javaweb.utils.connectionJDBCUtil;

@PropertySource("classpath:application.properties")
@Repository//dùng để nhận biết đây là data access
public class BuildingRepositoryImpl implements BuildingRepository
{
	@Value("${spring.datasource.url}")
	private String DB_URL;
	
	@Value("${spring.datasource.username}")
	private String USER;
	
	@Value("${spring.datasource.password}")
	private String PASS;
	
//	private BuildingRepository buildingRepository;
	
	public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
		Long staffId = buildingSearchBuilder.getStaffId();
		if(staffId != null){
			sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid");
		}
		List<String>typeCode = buildingSearchBuilder.getTypeCode();//chưa hiểu
		if(typeCode != null && typeCode.size()!= 0) {
			sql.append(" INNER JOIN buildingrenttype ON b.id = buildingrenttype.buildingid ");
			sql.append(" INNER JOIN renttype ON renttype.id = buildingrenttype.renttypeid ");
		}
//		String rentAreaTo = (String)params.get("areaTo");
//		String rentAreaFrom = (String)params.get("areaFrom");
//		if(StringUtil.checkString(rentAreaFrom)==true || StringUtil.checkString(rentAreaTo)==true) {
//			sql.append(" INNER JOIN rentarea ON rentarea.buildingid = b.id ");
//		}
	}
	
	public static void queryNormal (BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
//		for(Map.Entry<String, Object> it : params.entrySet()) {//lặp lấy từng phần tử trong building để rà điều kiện
//			if(!it.getKey().equals("staffId") && !it.getKey().equals("typeCode") && 
//					!it.getKey().startsWith("area") && !it.getKey().startsWith("rentPrice")) {
//				String value = it.getValue().toString();	
//				if(StringUtil.checkString(value)) {
//					if(NumberUtil.isNumber(value)==true) {
//						where.append(" AND b." + it.getKey() + " = " + value);
//					}
//					else {
//						where.append(" AND b." + it.getKey() + " LIKE '%" + value + "%' ");
//					}
//				}
//			}
//		}
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();//lấy các field các đối tượng
			for(Field item : fields) {
				item.setAccessible(true);//giúp set value, cho phép ánh xạ các fields của buildingSearchBuilder
				String fieldName = item.getName();
				if(!fieldName.equals("staffId") && !fieldName.equals("typeCode") && 
						!fieldName.startsWith("area") && !fieldName.startsWith("rentPrice")) {
					Object value = item.get(buildingSearchBuilder);
					if(value != null) {
						if(item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")
								|| item.getType().getName().equals("java.lang.Float")) {
							where.append(" AND b." +  fieldName + " = " + value);
						}
						else if(item.getType().getName().equals("java.lang.String")){
							where.append(" AND b." +  fieldName + " LIKE '%" + value + "%' ");
						}
					}
				}
			}
		}
		catch(Exception ex) {
			ex.getStackTrace();
		}
	}
	
	public static void querySpecial (BuildingSearchBuilder buildingSearchBuilder,StringBuilder where) {
		Long staffId = buildingSearchBuilder.getStaffId();
		if(staffId != null) {
			where.append(" AND assignmentbuilding.staffid = " + staffId); 
		}
		Long rentAreaTo = buildingSearchBuilder.getRentAreaTo();
		Long rentAreaFrom = buildingSearchBuilder.getRentAreaFrom();
		if(rentAreaTo != null || rentAreaFrom != null) {
			where.append(" AND EXISTS (SELECT * FROM rentarea r WHERE b.id  = r.buildingid ");
			if(rentAreaFrom != null) {
				where.append(" AND r.value >=" + rentAreaFrom);
			}
			if(rentAreaTo != null) {
				where.append(" AND r.value <=" + rentAreaTo);
			}
			where.append(") ");
		}
			
		Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
		Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
			if(rentPriceTo != null || rentPriceFrom != null) {
				if(rentPriceFrom != null) {
					where.append(" AND b.rentprice >=" + rentPriceFrom);
				}
				if(rentPriceTo != null) {
					where.append(" AND b.rentprice <=" + rentPriceTo);
				}
			}
			
			//thay cho java7/java8
//			if(typeCode != null && typeCode.size()!= 0) {// coi typeCode trong renttype
//				where.append(" AND renttype.code IN" + String.join(",", typeCode) + ")");
//			}
			
		//java 7
//		if(typeCode != null && typeCode.size()!= 0) {
//			 List<String> code = new ArrayList<>();
//			 for(String item:typeCode) {
//				 code.add("'" + item + "'");
//			 }
//			 where.append(" AND renttype.code IN(" + String.join(",",code) + ") ");
//		}
		
		//java 8 
		List<String>typeCode = buildingSearchBuilder.getTypeCode();
		if(typeCode != null && typeCode.size()!= 0) {
			where.append(" AND(");
			String sql = typeCode.stream().map(it -> "renttype.code LIKE" + "'%" + it + "%'").collect(Collectors.joining(" OR "));
			where.append(sql);
			where.append(" ) ");
		}
}
	
	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		StringBuilder sql = new StringBuilder( "SELECT b.id, b.name, b.street, b.ward, b.districtid, b.numberofbasement, b.floorarea, b.rentprice, b.managername, b.managerphone, b.servicefee, b.brokeragefee FROM building b ");//tạo String lệnh để lấy data từ BE
		
		joinTable(buildingSearchBuilder,sql);
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");//một hàm xử lí 1 công việc
		queryNormal(buildingSearchBuilder,where);
		querySpecial(buildingSearchBuilder,where);
		where.append("GROUP BY b.id;");
		sql.append(where);
		
		List<BuildingEntity> result = new ArrayList<>();
		try(Connection conn  = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());){//lấy dữ liệu
			
			while(rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setId(rs.getLong("b.id"));
				building.setName(rs.getString("b.name"));
				building.setWard(rs.getString("b.ward"));
				building.setDistrictid(rs.getLong("b.districtid"));
				building.setStreet(rs.getString("b.street"));
				building.setFloorArea(rs.getLong("b.floorarea"));
				building.setRentPrice(rs.getLong("b.rentprice"));
				building.setServiceFee(rs.getString("b.servicefee"));
				building.setBrokerageFee(rs.getLong("b.brokeragefee"));
				building.setManagerName(rs.getString("b.managername"));
				building.setManagerPhoneNumber(rs.getString("b.managerphone"));
				result.add(building);

			}
				
			
		}catch(SQLException e) {
			e.printStackTrace();
//			System.out.println("Connected database failed...");
		}
		return result;
	}

	
	
}