package com.cristhiane.familymoneytrackerapi.utils;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cristhiane.familymoneytrackerapi.domain.CartaoDeCredito;
import com.cristhiane.familymoneytrackerapi.domain.CategoriaDespesa;
import com.cristhiane.familymoneytrackerapi.domain.CategoriaReceita;
import com.cristhiane.familymoneytrackerapi.domain.Conta;
import com.cristhiane.familymoneytrackerapi.domain.Role;
import com.cristhiane.familymoneytrackerapi.domain.User;
import com.cristhiane.familymoneytrackerapi.enums.BandeiraCartaoDeCredito;
import com.cristhiane.familymoneytrackerapi.enums.TipoConta;
import com.cristhiane.familymoneytrackerapi.enums.TipoUsuario;
import com.cristhiane.familymoneytrackerapi.repository.CartaoDeCreditoRepository;
import com.cristhiane.familymoneytrackerapi.repository.CategoriaDespesaRepository;
import com.cristhiane.familymoneytrackerapi.repository.CategoriaReceitaRepository;
import com.cristhiane.familymoneytrackerapi.repository.ContaRepository;
import com.cristhiane.familymoneytrackerapi.repository.RoleRepository;
import com.cristhiane.familymoneytrackerapi.repository.UserRepository;

@Component
public class PopulateData {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	CategoriaDespesaRepository categoriaDespesaRepository;
	
	@Autowired
	CategoriaReceitaRepository categoriaReceitaRepository;
	
	@Autowired
	ContaRepository contaRepository;
	
	@Autowired
	CartaoDeCreditoRepository cartaoDeCreditoRepository;
	
	@PostConstruct
	public void insertData() {
		// Inserindo perfis de usuário
		Role role_group_user = new Role(TipoUsuario.USUARIO_COMUM);
		Role role_group_admin = new Role(TipoUsuario.ADMIN_GRUPO);

		// Inserindo usuários
		User group_admin = new User("group_admin", "group_admin@teste.com", encoder.encode("Gr0up@adm1n_T3st3"));
		group_admin.getRoles().add(role_group_admin);
		
		User group_user = new User("group_user", "group_user@teste.com", encoder.encode("Gr0upUs3r_T3st3"));
		group_user.getRoles().add(role_group_user);
		
		// Salvando perfis e usuários no banco de dados
		roleRepository.saveAll(Arrays.asList(role_group_user, role_group_admin));
		userRepository.saveAll(Arrays.asList(group_admin, group_user));
		
		//-------------------------------------------------------------------------
		// Inserindo categorias de despesas
		CategoriaDespesa supermercado = new CategoriaDespesa(null, "Supermercado", "Gastos com supermercado", 5000);
		CategoriaDespesa saude = new CategoriaDespesa(null, "Saúde", "Gastos com remédios e consultas", 2000);
		CategoriaDespesa lazer = new CategoriaDespesa(null, "Lazer", "Entretenimento, passeios, viagens, etc", 1000);
		CategoriaDespesa contas = new CategoriaDespesa(null, "Contas", "Contas fixas, como água, telefone, internet, etc", 2500);
		CategoriaDespesa transporte = new CategoriaDespesa(null, "Transporte", "Gasolina, corridas por aplicativo, etc", 1200);
		CategoriaDespesa vestuario = new CategoriaDespesa(null, "Vestuário", "Gastos com roupas", 500);
		CategoriaDespesa artigosLar = new CategoriaDespesa(null, "Artigos para o lar", "Artigos de cama, mesa e banho", 700);
		
		// Salvando categorias de despesas no banco de dados
		categoriaDespesaRepository.saveAll(Arrays.asList(supermercado, saude, lazer, contas, transporte, vestuario, artigosLar));
		
		//-------------------------------------------------------------------------
		// Inserindo categorias de receitas
		CategoriaReceita salario = new CategoriaReceita(null, "Salário", "Salário do mês");
		CategoriaReceita rendaExtra = new CategoriaReceita(null, "Renda Extra", "Ganhos além do salário mensal");
		CategoriaReceita rendimentoInvestimento = new CategoriaReceita(null, "Rendimento investimento", "Rendimentos provenientes de investimentos");
		
		// Salvando categorias de receitas no banco de dados
		categoriaReceitaRepository.saveAll(Arrays.asList(salario, rendaExtra, rendimentoInvestimento));
		
		//-------------------------------------------------------------------------
		// Inserindo contas
		Conta poupanca = new Conta(null, "Poupança da família", 5000, TipoConta.CONTA_POUPANCA);
		Conta dinheiro = new Conta(null, "Dinheiro em casa", 150, TipoConta.DINHEIRO);
		Conta mesada = new Conta(null, "Mesada das crianças", 75, TipoConta.MESADA);
		
		contaRepository.saveAll(Arrays.asList(poupanca, dinheiro, mesada));
		
		//-------------------------------------------------------------------------
		// Inserindo cartões de crédito
		CartaoDeCredito nubank = new CartaoDeCredito(null, "Nubank", BandeiraCartaoDeCredito.MASTERCARD, 5000, 13, 20);
		CartaoDeCredito bb = new CartaoDeCredito(null, "Banco do Brasil", BandeiraCartaoDeCredito.VISA, 2500, 11, 17);
		CartaoDeCredito santander = new CartaoDeCredito(null, "Santander", BandeiraCartaoDeCredito.ELO, 3200, 15, 25);
		
		cartaoDeCreditoRepository.saveAll(Arrays.asList(nubank, bb, santander));
	}
	
}
