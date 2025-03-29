package com.javaweb.converter;//converter: chuyển đổi dữ liệu

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component//dùng đánh dấu 1 bean( vì bean không có hàm khởi tạo)
public class BuildingDTOConverter {
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO building = modelMapper.map(item, BuildingDTO.class);//modelMapper tự set các biến cùng tên thay vì viết từng lệnh 37

		building.setAddress(item.getStreet()+","+item.getWard()+","+item.getDistrict().getName());//vì đã tạo mối liên kết 1-n, n-1 nên dễ dàng trỏ tới các entity khsac thay vì join
//		List<RentAreaEntity> rentAreas = rentAreaRepository.getValueByBuildingId(item.getId());//chuyển đổi dạng ("100,200,300") cho rentArea
		List<RentAreaEntity> rentAreas = item.getItems();//làm việc JPQL
		String areaResult = rentAreas.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
		building.setRentArea(areaResult);
		return building;
	}
}
