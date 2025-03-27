package com.javaweb.builder;

import java.util.ArrayList;
import java.util.List;


public class BuildingSearchBuilder {//hàm mẹ
	 private String name;
     private String street;
     private Long districtId;
     private String ward;
     private Long numberOfBasement;
     private String managerName;
     private String managerPhone;
     private Long rentPriceFrom;
     private Long rentPriceTo;
     private Long floorArea;
     private Long rentAreaFrom;
     private Long rentAreaTo;
     private Long staffId;
     private List<String>typeCode = new ArrayList<>();
     
     //constructor
     private BuildingSearchBuilder(Builder builder) {
         this.name = builder.name;
         this.street = builder.street;
         this.districtId = builder.districtCode;
         this.ward = builder.ward;
         this.numberOfBasement = builder.numberOfBasement;
         this.managerName = builder.managerName;
         this.managerPhone = builder.managerPhone;
         this.rentPriceFrom = builder.rentPriceFrom;
         this.rentPriceTo = builder.rentPriceTo;
         this.floorArea = builder.floorArea;
         this.rentAreaFrom = builder.rentAreaFrom;
         this.rentAreaTo = builder.rentAreaTo;
         this.staffId = builder.staffId;
         this.typeCode = builder.typeCode;
     }
     
     public Long getDistrictId() {
    	 return districtId;
     }
     public String getName() {
         return name;
     }

     public String getStreet() {
         return street;
     }


     public String getWard() {
         return ward;
     }

     public Long getNumberOfBasement() {
         return numberOfBasement;
     }

     public String getManagerName() {
         return managerName;
     }

     public String getManagerPhone() {
         return managerPhone;
     }

     public Long getRentPriceFrom() {
         return rentPriceFrom;
     }

     public Long getRentPriceTo() {
         return rentPriceTo;
     }

     public Long getFloorArea() {
         return floorArea;
     }

     public Long getRentAreaFrom() {
         return rentAreaFrom;
     }

     public Long getRentAreaTo() {
         return rentAreaTo;
     }

     public Long getStaffId() {
         return staffId;
     }

     public List<String> getTypeCode() {
         return typeCode;
     }
     
     
     public static class Builder{
         private String name;
         private String street;
         private Long districtId;
         private String ward;
         private Long numberOfBasement;
         private String managerName;
         private String managerPhone;
         private Long rentPriceFrom;
         private Long rentPriceTo;
         private Long floorArea;
         private Long rentAreaFrom;
         private Long rentAreaTo;
         private Long staffId;
         private List<String>typeCode = new ArrayList<>();
         
         public Builder setName(String name) {
        	 this.name = name;
        	 return this;
         }
         public Builder setStreet(String street) {
        	 this.street = street;
        	 return this;
         }
         public Builder setDistristId(Long districtId) {
        	 this.districtId = districtId;
        	 return this;
         }
         public Builder setWard(String ward) {
        	 this.ward = ward;
        	 return this;
         }
         public Builder setNumberOfBasement(Long numberOfBasement) {
        	 this.numberOfBasement = numberOfBasement;
        	 return this;
         }
         public Builder setManagerName(String managerName) {
        	 this.managerName = managerName;
        	 return this;
         }
         public Builder setManagerPhoneNumber(String managerPhone) {
        	 this.managerPhone = managerPhone;
        	 return this;
         }
         public Builder setRentPriceFrom(Long rentPriceFrom) {
        	 this.rentPriceFrom = rentPriceFrom;
        	 return this;
         }
         public Builder setFloorArea(Long floorArea) {
        	 this.floorArea = floorArea;
        	 return this;
         }
         public Builder setrentPriceTo(Long rentPriceTo) {
        	 this.rentPriceTo = rentPriceTo;
        	 return this;
         }
         public Builder setRentAreaFrom(Long rentAreaFrom) {
        	 this.rentAreaFrom = rentAreaFrom;
        	 return this;
         }
         public Builder setRentAreaTo(Long rentAreaTo) {
        	 this.rentAreaTo = rentAreaTo;
        	 return this;
         }
         public Builder setStaffId(Long staffId) {
        	 this.staffId = staffId;
        	 return this;
         }
         public Builder setTypeCode(List<String> typeCode) {
        	 this.typeCode = typeCode;
        	 return this;
         }
         
         //phương thức trả về đối tượng 
         public BuildingSearchBuilder build() {
        	 return new BuildingSearchBuilder(this);
         }
     }
     
}
