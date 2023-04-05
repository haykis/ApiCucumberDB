package pojo;

import java.util.List;

public class RegisterMedunna {
	private String lastName;
	private String lastModifiedDate;
	private String lastModifiedBy;
	private String login;
	private List<String> authorities;
	private String ssn;
	private String firstName;
	private String password;
	private String createdDate;
	private String langKey;
	private String createdBy;
	private String imageUrl;
	private int id;
	private String email;
	private boolean activated;

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setLastModifiedDate(String lastModifiedDate){
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedDate(){
		return lastModifiedDate;
	}

	public void setLastModifiedBy(String lastModifiedBy){
		this.lastModifiedBy = lastModifiedBy;
	}

	public String getLastModifiedBy(){
		return lastModifiedBy;
	}

	public void setLogin(String login){
		this.login = login;
	}

	public String getLogin(){
		return login;
	}

	public void setAuthorities(List<String> authorities){
		this.authorities = authorities;
	}

	public List<String> getAuthorities(){
		return authorities;
	}

	public void setSsn(String ssn){
		this.ssn = ssn;
	}

	public String getSsn(){
		return ssn;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setCreatedDate(String createdDate){
		this.createdDate = createdDate;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public void setLangKey(String langKey){
		this.langKey = langKey;
	}

	public String getLangKey(){
		return langKey;
	}

	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}

	public String getCreatedBy(){
		return createdBy;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setActivated(boolean activated){
		this.activated = activated;
	}

	public boolean isActivated(){
		return activated;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"lastName = '" + lastName + '\'' + 
			",lastModifiedDate = '" + lastModifiedDate + '\'' + 
			",lastModifiedBy = '" + lastModifiedBy + '\'' + 
			",login = '" + login + '\'' + 
			",authorities = '" + authorities + '\'' + 
			",ssn = '" + ssn + '\'' + 
			",firstName = '" + firstName + '\'' + 
			",password = '" + password + '\'' + 
			",createdDate = '" + createdDate + '\'' + 
			",langKey = '" + langKey + '\'' + 
			",createdBy = '" + createdBy + '\'' + 
			",imageUrl = '" + imageUrl + '\'' + 
			",id = '" + id + '\'' + 
			",email = '" + email + '\'' + 
			",activated = '" + activated + '\'' + 
			"}";
		}
}