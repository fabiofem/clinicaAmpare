package br.com.clinica.tabelas;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Funcionario extends Pessoa{


	private static final long serialVersionUID = -3428441263019897677L;
	@Enumerated(EnumType.STRING)
    private Funcao funcao;
	
	public Funcao getFuncao() {
		return funcao;
	}
	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((funcao == null) ? 0 : funcao.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (funcao != other.funcao)
			return false;
		return true;
	}
	
	
    
    
}
