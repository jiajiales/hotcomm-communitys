package com.hotcomm.community.common.bean.en.house;

public class HouseEN {

	public enum BuildingRentOrSale {
		RENT(1, "租赁"), SALE(2, "购买"), RENTORSALE(3, "租赁购买");

		private Integer value;
		private String name;

		private BuildingRentOrSale(Integer value, String name) {
			this.value = value;
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public static BuildingRentOrSale getByValue(Integer value) {

			for (BuildingRentOrSale obj : BuildingRentOrSale.values()) {
				if (obj.getValue() == value) {
					return obj;
				}
			}
			return null;
		}
	}

	public enum RoomRentOrSale {
		RENT(1, "租赁"), SALE(2, "购买");

		private Integer value;
		private String name;

		private RoomRentOrSale(Integer value, String name) {
			this.value = value;
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public static RoomRentOrSale getByValue(Integer value) {
			for (RoomRentOrSale obj : RoomRentOrSale.values()) {
				if (obj.getValue() == value) {
					return obj;
				}
			}
			return null;
		}
	}

	public enum RoomAttribute {
		BUSINESS(1, "商业型"), RESIDENCE(2, "住宅型"), SERVICE(3, "服务型");

		private Integer value;
		private String name;

		private RoomAttribute(Integer value, String name) {
			this.value = value;
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public static RoomAttribute getByValue(Integer value) {
			for (RoomAttribute obj : RoomAttribute.values()) {
				if (obj.getValue() == value) {
					return obj;
				}
			}
			return null;
		}

	}
}
