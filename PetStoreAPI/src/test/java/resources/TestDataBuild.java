package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Details;
import pojo.NewPet;



public class TestDataBuild {

	public NewPet createPet(String id, String name, String status)
	{
		NewPet pet =new NewPet();
		pet.setId(id);
		pet.setStatus(status);
		pet.setName(name);
		pet.setPhotoUrls(new String[] {"String"});
		
		Details dt = new Details();
		dt.setSecId("7");
		dt.setSecName("String");
		pet.setCategory(dt);
		
		Details dt2[] = new Details[]{dt};
		pet.setTags(dt2);
		
		return pet;
	}
	
}
