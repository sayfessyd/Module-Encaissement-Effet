package com.pfe.security;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.pfe.model.Personnel;
import com.pfe.service.PersonnelService;

@ManagedBean(name = "LoginCtrl")
@SessionScoped
public class LoginManagedBean {

	private String userName = "2997";
	private String password;
	private Long codeAgence = 120L;
	static Locale locale = Locale.getDefault();
	static Date actuelle = new Date();
	static Date contable = new Date();
	static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private String dateC = dateFormat.format(actuelle);
	private String dateJ = dateFormat.format(actuelle);

	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager authenticationManager;

	@ManagedProperty(value = "#{PersonnelService}")
	private PersonnelService pService;

	public String login() {
		try {
			Authentication request = new UsernamePasswordAuthenticationToken(this.getUserName(), this.getPassword());
			Authentication result = authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			System.out.println("TEST AUTH FAILURE");
			warn("Nom d'utilisateur ou mot de passe invalide.");
			return "";
		}
		System.out.println("TEST AUTH SUCCESS");
		Personnel p = pService.findPersonnel(userName);
		if (p != null) {
			codeAgence = p.getStructure().getCodStrcStrc();
			contable = p.getStructure().getDateJournee();
			if (contable != null)
				dateC = dateFormat.format(contable);
			else
				dateC = dateFormat.format(actuelle);
			dateJ = dateFormat.format(actuelle);

		}
		return "/pages/secure/index.xhtml?faces-redirect=true";
	}

	public String logout() {
		SecurityContextHolder.clearContext();
		System.out.println("LOGOUT");
		return "/pages/unsecure/login.xhtml?faces-redirect=true";
	}

	public void info(String title, String message) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, title, message));

	}

	public void warn(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Attention !", message));
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public PersonnelService getpService() {
		return pService;
	}

	public void setpService(PersonnelService pService) {
		this.pService = pService;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getCodeAgence() {
		return codeAgence;
	}

	public void setCodeAgence(Long codeAgence) {
		this.codeAgence = codeAgence;
	}

	public String getDateC() {
		return dateC;
	}

	public void setDateC(String dateC) {
		this.dateC = dateC;
	}

	public String getDateJ() {
		return dateJ;
	}

	public void setDateJ(String dateJ) {
		this.dateJ = dateJ;
	}

	public static Date getActuelle() {
		return actuelle;
	}

	public static void setActuelle(Date actuelle) {
		LoginManagedBean.actuelle = actuelle;
	}

	public static DateFormat getDateFormat() {
		return dateFormat;
	}

	public static void setDateFormat(DateFormat dateFormat) {
		LoginManagedBean.dateFormat = dateFormat;
	}

	public static Date getContable() {
		return contable;
	}

	public static void setContable(Date contable) {
		LoginManagedBean.contable = contable;
	}

}