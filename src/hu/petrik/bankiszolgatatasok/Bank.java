package hu.petrik.bankiszolgatatasok;

import java.util.ArrayList;
import java.util.List;

public class Bank {
	private List<Szamla> szamlaLista;

	public Bank() {
		this.szamlaLista=new ArrayList<>();
	}

	public Szamla szamlaNyitas(Tulajdonos tulajdonos, int hitelKeret) {
		if (hitelKeret > 0) {
			HitelSzamla h1= new HitelSzamla(tulajdonos, hitelKeret);
			szamlaLista.add(h1);
			return h1;
		} else if (hitelKeret == 0) {
			MegtakaritasiSzamla m1= new MegtakaritasiSzamla(tulajdonos);
			szamlaLista.add(m1);
			return m1;
		} else {
			throw new IllegalArgumentException("a hitelkeret nem lehet kisebb mint 0");
		}
	}

	public int getOsszEgyenleg(Tulajdonos tulajdonos) {
		int osszegyenleg = 0;
		for (Szamla szamla : szamlaLista) {
			if (szamla.getTulajdonos() == tulajdonos) {
				osszegyenleg += szamla.getAktualisEgyenleg();
			}
		}
		return osszegyenleg;
	}

	public Szamla getLegnagyobbEgyenleguSzamla(Tulajdonos tulajdonos) {
		int max=0;
		for (int i = 1; i < szamlaLista.size(); i++) {
			if (szamlaLista.get(i).getTulajdonos() == tulajdonos && szamlaLista.get(i).getAktualisEgyenleg() > szamlaLista.get(max).getAktualisEgyenleg()){
				max=i;
			}
		}
		return szamlaLista.get(max);
	}
	public long getOsszHitelkeret(){
		int osszeg = 0;
		for (Szamla szamla : szamlaLista){
			if (szamla instanceof HitelSzamla){
				osszeg+=((HitelSzamla) szamla).getHitelKeret();
			}
		}
		return osszeg;
	}
}
