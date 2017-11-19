package br.com.clinica.tabelas;

import javax.persistence.OneToOne;

public class Medico extends Pessoa{

	private static final long serialVersionUID = 8667084330728332794L;
    private Tratamento pronomeTratamento;
    private boolean ativo;
    @OneToOne
    private AtividadeProfissional atividadeProfissional;
    
    public Tratamento getPronomeTratamento() {
		return pronomeTratamento;
	}
	public void setPronomeTratamento(Tratamento pronomeTratamento) {
		this.pronomeTratamento = pronomeTratamento;
	}
	
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result + ((pronomeTratamento == null) ? 0 : pronomeTratamento.hashCode());
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
		Medico other = (Medico) obj;
		if (ativo != other.ativo)
			return false;
		if (pronomeTratamento != other.pronomeTratamento)
			return false;
		return true;
	}
		
	
	
}
