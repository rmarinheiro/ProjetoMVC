package com.rafael.projetomvc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.rafael.projetomvc.dominio.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preenchePagamentoComBoleto(PagamentoComBoleto pagto , Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimneto(cal.getTime());
		
		
	}

}
