package pojo;

import java.util.List;

public class NewPet {

	private String id;
	private String name;
	private String status;
	private Details category;
	private String photoUrls[];
	private Details tags[];
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Details getCategory() {
		return category;
	}
	public void setCategory(Details category) {
		this.category = category;
	}
	public String[] getPhotoUrls() {
		return photoUrls;
	}
	public void setPhotoUrls(String[] photoUrls) {
		this.photoUrls = photoUrls;
	}
	public Details[] getTags() {
		return tags;
	}
	public void setTags(Details[] tags) {
		this.tags = tags;
	}
	

}