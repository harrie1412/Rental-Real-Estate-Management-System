package com.javaweb.converter;//converter: chuyển đổi dữ liệu

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component//dùng đánh dấu 1 bean( vì bean không có hàm khởi tạo)
public class BuildingDTOConverter {
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO building = modelMapper.map(item, BuildingDTO.class);//modelMapper tự set các biến cùng tên thay vì viết từng lệnh 37
		DistrictEntity districtEntity = districtRepository.findNameById(item.getDistrictid());
		building.setAddress(item.getStreet()+","+item.getWard());
		List<RentAreaEntity> rentAreas = rentAreaRepository.getValueByBuildingId(item.getId());//chuyển đổi dạng ("100,200,300") cho rentArea
		String areaResult = rentAreas.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
		building.setRentArea(areaResult);
		
//		building.setName(item.getName());
//		building.setBrokerageFee(item.getBrokerageFee());
//		building.setEmptyArea(item.getEmptyArea());;
//		building.setManagerName(item.getManagerName());;
//		building.setFloorArea(item.getFloorArea());;
//		building.setManagerPhone(item.getManagerPhoneNumber());
		
		return building;
	}
}
