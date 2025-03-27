package com.javaweb.converter;//chuyển đổi dữ liệu

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;
@Component
public class BuildingSearchBuilderConverter {
	public BuildingSearchBuilder toBuildingSearchBuilder (Map<String,Object> params,List<String> typeCode) {
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()//builder partem: khi dữ liệu khác map, dữ liệu phức tạp, dùng design partem ( chứa builder) dễ dàng hơn
																				.setName(MapUtil.getObject(params, "name", String.class))//để định nghĩa lại name có đúng string
																				.setFloorArea(MapUtil.getObject(params, "floorArea",Long.class))
																				.setWard(MapUtil.getObject(params, "ward", String.class))
															                    .setStreet(MapUtil.getObject(params, "street", String.class))
															                    .setDistristId(MapUtil.getObject(params, "districtId", Long.class))
															                    .setNumberOfBasement(MapUtil.getObject(params, "numberOfBasement", Long.class))
															                    .setTypeCode(typeCode)
															                    .setManagerName(MapUtil.getObject(params, "managerName", String.class))
															                    .setManagerPhoneNumber(MapUtil.getObject(params, "managerPhone", String.class))
															                    .setRentPriceFrom(MapUtil.getObject(params, "rentPriceFrom", Long.class))
															                    .setrentPriceTo(MapUtil.getObject(params, "rentPriceTo", Long.class))
															                    .setRentAreaFrom(MapUtil.getObject(params, "rentAreaFrom", Long.class))
															                    .setRentAreaTo(MapUtil.getObject(params, "rentAreaTo", Long.class))
															                    .setStaffId(MapUtil.getObject(params, "staffid", Long.class))
															                    .build();
		return buildingSearchBuilder;
	}
}
