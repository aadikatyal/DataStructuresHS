package maps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

public class Citizen implements Comparable<Citizen>
{
	private String firstName, lastName, street, relation, ownership, gender, maritalStatus, birthplace, fatherBirthplace, motherBirthplace, motherTongue, occupation, industry, transcriberRemarks;
	private Integer streetNumber, yearImmigrated, ageFirstMarried;
	private Boolean attendSchool, canRead;
	private Double age, propertyValue;
	
	public Citizen(String fileRecord) throws Exception
	{
		this.street = sanitizeString(fileRecord.substring(20, 36));
		this.streetNumber = sanitizeInt(fileRecord.substring(36, 45));
		this.lastName = sanitizeString(fileRecord.substring(55, 71));
		this.firstName = sanitizeString(fileRecord.substring(71, 88));
		this.relation = sanitizeString(fileRecord.substring(88, 108));
		this.ownership = sanitizeString(fileRecord.substring(108, 113));
		this.propertyValue = sanitizeDouble(fileRecord.substring(113, 121));
		this.gender = sanitizeString(fileRecord.substring(133, 138));
		this.age = sanitizeDouble(fileRecord.substring(143, 151));
		this.maritalStatus = sanitizeString(fileRecord.substring(151, 156));
		this.ageFirstMarried = sanitizeInt(fileRecord.substring(156, 162));
		this.attendSchool = sanitizeBoolean(fileRecord.substring(162, 168));
		this.canRead = sanitizeBoolean(fileRecord.substring(168, 173));
		this.birthplace = sanitizeString(fileRecord.substring(173, 190));
		this.fatherBirthplace = sanitizeString(fileRecord.substring(190, 207));
		this.motherBirthplace = sanitizeString(fileRecord.substring(207, 224));
		this.motherTongue = sanitizeString(fileRecord.substring(224, 235));
		this.yearImmigrated = sanitizeInt(fileRecord.substring(235, 241));
		this.occupation = sanitizeString(fileRecord.substring(252, 274));
		this.industry = sanitizeString(fileRecord.substring(274, 303));
		this.transcriberRemarks = sanitizeString(fileRecord.substring(342));
	}
	
	private String sanitizeString(String str) 
	{
		if(str == null)
		{
			return "";
		}
		
		// If str is . return null else the same str
		return str.trim().equals(".") ? "" : str.trim();
	}
	
	private Integer sanitizeInt(String str) 
	{
		if(str == null)
		{
			return -1;
		}
		
		Integer value = null;
		
		try
		{
			value = Integer.parseInt(str.trim());
		}
		catch(NumberFormatException e)
		{
			value = -1;
		}
		
		return value;
	}
	
	public Double sanitizeDouble(String str) 
	{
		if(str == null)
		{
			return null;
		}
		
		Double age = 0.0;
		
		try
		{
			age = Double.parseDouble(str);
		}
		catch(Exception e)
		{
			age = -1.0;
		}
		return age;
	}
	
	public Boolean sanitizeBoolean(String str)
	{
		if(str == null)
		{
			return null;
		}
		
		if(str.trim().equals("Yes"))
		{
			return true;
		}
		else if(str.trim().equals("No"))
		{
			return false;
		}
		
		return false;
	}
	
	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getStreet()
	{
		return street;
	}

	public Integer getStreetNumber()
	{
		return streetNumber;
	}

	public String getRelation()
	{
		return relation;
	}

	public String getOwnership()
	{
		return ownership;
	}

	public String getGender()
	{
		return gender;
	}

	public Double getAge()
	{
		return age;
	}

	public Integer getAgeFirstMarried()
	{
		return ageFirstMarried;
	}

	public String getMaritalStatus()
	{
		return maritalStatus;
	}

	public String getBirthplace()
	{
		return birthplace;
	}

	public String getFatherBirthplace()
	{
		return fatherBirthplace;
	}

	public String getMotherBirthplace()
	{
		return motherBirthplace;
	}

	public String getMotherTongue()
	{
		return motherTongue;
	}

	public String getOccupation()
	{
		return occupation;
	}

	public String getIndustry()
	{
		return industry;
	}

	public String getTranscriberRemarks()
	{
		return transcriberRemarks;
	}

	public Double getPropertyValue()
	{
		return propertyValue;
	}

	public Boolean getAttendSchool()
	{
		return attendSchool;
	}

	public Boolean getCanRead()
	{
		return canRead;
	}

	public Integer getYearImmigrated()
	{
		return yearImmigrated;
	}
	
	

	@Override
	public String toString() 
	{
		return "Citizen [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", street=" + street
				+ ", relation=" + relation + ", ownership=" + ownership + ", gender=" + gender + ", maritalStatus="
				+ maritalStatus + ", birthplace=" + birthplace + ", fatherBirthplace=" + fatherBirthplace
				+ ", motherBirthplace=" + motherBirthplace + ", motherTongue=" + motherTongue + ", occupation="
				+ occupation + ", industry=" + industry + ", transcriberRemarks=" + transcriberRemarks
				+ ", propertyValue=" + propertyValue + ", streetNumber=" + streetNumber + ", yearImmigrated="
				+ yearImmigrated + ", ageFirstMarried=" + ageFirstMarried + ", attendSchool=" + attendSchool
				+ ", canRead=" + canRead + "]";
	}

	@Override
	public int compareTo(Citizen otherCitizen)
	{
		return Comparator.comparing(Citizen::getFirstName).thenComparing(Citizen::getLastName).thenComparing(Citizen::getStreet).thenComparing(Citizen::getStreetNumber).thenComparing(Citizen::getRelation).thenComparing(Citizen::getOwnership).thenComparing(Citizen::getPropertyValue).thenComparing(Citizen::getGender).thenComparing(Citizen::getAge).thenComparing(Citizen::getMaritalStatus).thenComparing(Citizen::getAgeFirstMarried).thenComparing(Citizen::getAttendSchool).thenComparing(Citizen::getCanRead).thenComparing(Citizen::getBirthplace).thenComparing(Citizen::getFatherBirthplace).thenComparing(Citizen::getMotherBirthplace).thenComparing(Citizen::getMotherTongue).thenComparing(Citizen::getYearImmigrated).thenComparing(Citizen::getOccupation).thenComparing(Citizen::getIndustry).thenComparing(Citizen::getTranscriberRemarks).compare(this, otherCitizen);
	}
}
