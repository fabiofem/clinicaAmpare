package br.com.clinica.tabelas;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Pessoa implements Serializable{

	private static final long serialVersionUID = 3368987062424527532L;
	@Id  
	@GeneratedValue
    int id;
	@OneToOne(cascade=CascadeType.ALL)
	private Impressao impressao;
	private String nomeCompleto;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar datadeNascimento;
    private boolean sexo;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar localNascimento;
    private String nomeMae;
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;
    private String profissao;
    private String rg;
    @Column(unique=false)
    private String cpf;
    private String pathImagem;
    private String fone;
    private String email;
    
    @OneToOne
    private Endereco endereco;
	
    private String nomeFamiliarResponsavel;
    
    

    

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Calendar getDatadeNascimento() {
		return datadeNascimento;
	}

	public void setDatadeNascimento(Calendar datadeNascimento) {
		this.datadeNascimento = datadeNascimento;
	}

	public boolean isSexo() {
		return sexo;
	}

	public void setSexo(boolean sexo) {
		this.sexo = sexo;
	}

	public Calendar getLocalNascimento() {
		return localNascimento;
	}

	public void setLocalNascimento(Calendar localNascimento) {
		this.localNascimento = localNascimento;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}



	public String getPathImagem() {
		return pathImagem;
	}

	public void setPathImagem(String pathImagem) {
		this.pathImagem = pathImagem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nomeCompleto == null) ? 0 : nomeCompleto.hashCode());
		result = prime * result + (sexo ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id != other.id)
			return false;
		if (nomeCompleto == null) {
			if (other.nomeCompleto != null)
				return false;
		} else if (!nomeCompleto.equals(other.nomeCompleto))
			return false;
		if (sexo != other.sexo)
			return false;
		return true;
	}

	public String getNomeFamiliarResponsavel() {
		return nomeFamiliarResponsavel;
	}

	public void setNomeFamiliarResponsavel(String nomeFamiliarResponsavel) {
		this.nomeFamiliarResponsavel = nomeFamiliarResponsavel;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public void setImpressao(Impressao impressao2) {
		this.impressao =impressao2;
		if(this.impressao != null){
		this.impressao.setPessoa(this);
		}
		
	}
	
    public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Impressao getImpressao() {
		return impressao;
	}
    
    


}
