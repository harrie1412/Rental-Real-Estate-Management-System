package com.javaweb.api;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.model.ErrorResponseDTO;
import com.javaweb.service.BuildingService;
import com.javaweb.service.impl.BuildingServiceImpl;

import CustomException.FieldRequiredException;
import java.sql.Statement;
//@Controller
@RestController
public class BuildingAPI {
	//Param : dùng tìm kiếm
	//Body: thêm, sửa, xóa
	//required=true; là mặc định phải có
	
	
	
	
//	Lấy giá trị từ client
	
//		C1:Dùng Pagram: Dùng cho số lượng ít value trên param, lấy dữ liệu từ client
//    @RequestMapping(value="/api/building/",method=RequestMethod.GET)
//    public void getBuilding1(@RequestParam(value="name", required=false) String nameBuilding,
//    						@RequestParam(value="numberOfBasement",required=false) Integer numberOfBasement,
//    						@RequestParam(value="ward") String ward) {
//    	System.out.println(nameBuilding +" "+numberOfBasement + " "+ward);
//    }
    
//  C2:Dùng Body & Pagram: Dùng cho số lượng value quá nhiều trên param, lấy dữ liệu từ client
//    @RequestMapping(value="/api/building/",method=RequestMethod.POST)
//    public void getBuilding2(@RequestParam Map<String,String> params ) {//@RequestBody nếu là dùng body đề truyền xuống backend
//    	System.out.println("ok");
//    }
	
	//C3:Dùng body: Có thể lấy dữ liệu từ client về dạng JSON ở BuildingDTO
//	@RequestMapping(value="/api/building/",method=RequestMethod.POST)
//	public void getBuilding(@RequestBody BuildingDTO buildingDTO) {
//		System.out.println("ok");
//	}
	
	
	
	
//	Trả giá trị lại client
	
//	C1:Có thể dùng JavaBean để thay thế Map<,>(giống nhau về hình thức JSON)
//	@RequestMapping(value="/api/building/",method=RequestMethod.GET)
//	//Có thể thay thế dòng 47 như: @GetMapping(value="/api/building/")
//	@ResponseBody//phải bắt buộc ghi để mã hóa json đưa lên lại cho client
//	public BuildingDTO getBuilding1(@RequestParam(value="name", required=false) String nameBuilding,
//  						@RequestParam(value="numberOfBasement",required=false) Integer numberOfBasement,
//  						@RequestParam(value="ward") String ward) {
//		BuildingDTO result=new BuildingDTO();
//		result.setName(nameBuilding);
//		result.setNumberOfBasement(numberOfBasement);
//		result.setWard(ward);
//		return result;
//	}
	
	//C2:Dùng body: Có thể lấy dữ liệu từ client về dạng JSON ở BuildingDTO và truyền lại dữ liệu từ DTO lên
//	@RequestMapping(value="/api/building/",method=RequestMethod.POST)
//	@ResponseBody
//	public BuildingDTO getBuilding(@RequestBody BuildingDTO buildingDTO) {
//		return buildingDTO;//trả về những gì có trong BuildingDTO
//	}
	
//	C3:Dùng Body & Pagram: Dùng cho số lượng value quá nhiều trên param, lấy dữ liệu từ client
//  @RequestMapping(value="/api/building/",method=RequestMethod.POST)
//  @ResponseBody
//  public Map<String,String> getBuilding2(@RequestBody Map<String,String> params ) {
//  	return params;//chỉ trả về những gì đã lấy từ client
	
//	C4: trả về 1 list DTO từ backend về client
//	@GetMapping(value="/api/building/")
//	public List<BuildingDTO> getBuildings(@RequestParam(value="name",required=false) String name,
//											@RequestParam(value="numberOfBasement",required=false) Integer numberOfBasement,
//											@RequestParam(value="ward",required=false) String ward){
//		List<BuildingDTO> listBuildings = new ArrayList<>();
//		BuildingDTO buildingDTO1 = new BuildingDTO();
//		buildingDTO1.setName("VCB Building");
//		buildingDTO1.setNumberOfBasement(2);
//		buildingDTO1.setWard("HCM");
//		BuildingDTO buildingDTO2 = new BuildingDTO();
//		buildingDTO2.setName("BIDV Tower");
//		buildingDTO2.setNumberOfBasement(3);
//		buildingDTO2.setWard("TL");
//		listBuildings.add(buildingDTO1);
//		listBuildings.add(buildingDTO2);
//		return listBuildings;
//	}
	
	
	
//	Xóa API
	
//	C1:@DeleteMapping(value="/api/building/{id}")
//	public void deleteBuilding(@PathVariable Integer id) {
//		System.out.println("Đã xóa tòa nhà có id = "+ id+ " rồi nhé!");
//	}
	
//	id và name là data value cố định: thường chỉ có 1 hoặc 2
//	C2:@DeleteMapping(value="/api/building/{id}/{name}")//ràng buộc thêm điều kiện phải id VÀ name
//	public void deleteBuilding(@PathVariable Integer id,
//								@PathVariable String name) {
//		System.out.println("Đã xóa tòa nhà có id = "+ id+" và có name = "+name+" rồi nhé!");
//	}
	
	
//	C3:@DeleteMapping(value="/api/building/{id}/{name}/")//ràng buộc thêm điều kiện các key trong params
//	public void deleteBuilding(@PathVariable Integer id,
//								@PathVariable String name,
//								@RequestParam(value="ward",required=false) String ward) {
//		System.out.println("Đã xóa tòa nhà có id = "+ id+" và có name = "+name+" và ward= "+ward+" rồi nhé!");
//	}
	
	
	
	//Video 7: học 13 restfull API & tìm và xử lí lỗi khi lấy data từ FE
//	@PostMapping(value="/api/building/")//thêm mới tòa nhà
//	public Object getBuildings(@RequestBody BuildingDTO building){
//		//Xu li duoi DB xong roi
//		try {  136--152: thay try catch bằng cách tạo controllerAvide(video 8)
//			System.out.println(5/0);
//			valiDate(building);
//		}
//		catch(Exception e) {
//			ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
//			errorResponseDTO.setError(e.getMessage());
//			List<String> details = new ArrayList<>();
//			details.add("Check lại name hoặc numberOfBasement đi bởi vì đang bị null đó!");
//			errorResponseDTO.setDetail(details);
//			return errorResponseDTO;
//		}
//		
//		BuildingDTO result = new BuildingDTO();
//		result.setName("VCB Building");
//		result.setNumberOfBasement(2);
//		result.setWard("HCM");
//		return result;
//	}
//	public void valiDate(BuildingDTO buildingDTO){
//		if(buildingDTO.getName()==null || buildingDTO.getName().equals("") || buildingDTO.getNumberOfBasement() == null){
//			throw new FieldRequiredException("name or numberBasement is null");
//		}
//	}
	
	
//	Video 8: thiết lập adviceException thay cho try catch, kiểm tra data từ FE
//		@PostMapping(value="/api/building/")//thêm mới tòa nhà
//		public Object getBuildings(@RequestBody BuildingDTO building){
//			//Xu li duoi DB xong roi
//			valiDate(building);
//			return null;
//		}
//		public void valiDate(BuildingDTO buildingDTO){
//			if(buildingDTO.getName()==null || buildingDTO.getName().equals("") || buildingDTO.getNumberOfBasement() == null){
//				throw new FieldRequiredException("name or numberBasement is null");
//			}
//		}
	
	//Video 10
//	@Autowired // giúp hiểu được interface khai báo ở dòng 185
//	private BuildingService buildingService;
//
//	@DeleteMapping(value="/api/building/")//xoá tòa nhà
//	public List<BuildingDTO> getBuildings(@RequestParam(name="name", required= false) String name,
//											@RequestParam(name="districtid", required = false) Long district,
//											@RequestParam(name="typeCode", required=false)  List<String>typeCode){//lấy name ở FE, đặc biệt name(key)=name
//		List<BuildingDTO> result = buildingService.findAll(name, district);		
//		return result;// trả dữ liệu
//	}
	
//	Video 9: Mô hình MVC & layer 3 lớp khi lấy data từ BE
	
//	@Autowired // giúp hiểu được interface khai báo ở dòng 185
//	private BuildingService buildingService;
//	
//	@GetMapping(value="/api/building/")//thêm mới tòa nhà
//	public List<BuildingDTO> getBuildings(@RequestParam(name="name") String name){//lấy name ở FE, đặc biệt name(key)=name
//		List<BuildingDTO> result = buildingService.findAll(name);		
//		return result;// trả dữ liệu
//	}
	
	//Video 11
//	@Autowired // giúp hiểu được interface khai báo ở dòng 185
//	private BuildingService buildingService;
//	
//	@GetMapping(value="/api/building/")//xoá tòa nhà
//	public List<BuildingDTO> getBuildings(@RequestParam Map<String,Object> params){//lấy name ở FE, đặc biệt name(key)=name
//		List<BuildingDTO> result = buildingService.findAll(params);		
//		return result;// trả dữ liệu
//	}
	
	
	
	//Video 12
		@Autowired // giúp hiểu được interface khai báo ở dòng 185
		private BuildingService buildingService;
		
		@GetMapping(value="/api/building/")//xoá tòa nhà
		public List<BuildingDTO> getBuildings(@RequestParam Map<String,Object> params,
												@RequestParam (name="typeCode", required=false) List<String> typeCode){//lấy name ở FE, đặc biệt name(key)=name
			List<BuildingDTO> result = buildingService.findAll(params,typeCode);		
			return result;// trả dữ liệu
		}
	
	
	
}

