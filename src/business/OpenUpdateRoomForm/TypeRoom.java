package business.OpenUpdateRoomForm;

public class TypeRoom {
	private int typeRoomId;
	private String nameTypeRoom;
	private String description;
	
	public TypeRoom(int typeRoomId, String nameTypeRoom, String description) {
		super();
		this.typeRoomId = typeRoomId;
		this.nameTypeRoom = nameTypeRoom;
		this.description = description;
	}

	public int getTypeRoomId() {
		return typeRoomId;
	}

	public String getNameTypeRoom() {
		return nameTypeRoom;
	}

	public String getDescription() {
		return description;
	}

	public void setTypeRoomId(int typeRoomId) {
		this.typeRoomId = typeRoomId;
	}

	public void setNameTypeRoom(String nameTypeRoom) {
		this.nameTypeRoom = nameTypeRoom;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
	
}
