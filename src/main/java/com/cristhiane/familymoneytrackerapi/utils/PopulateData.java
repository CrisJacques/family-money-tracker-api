package com.cristhiane.familymoneytrackerapi.utils;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cristhiane.familymoneytrackerapi.domain.CategoriaDespesa;
import com.cristhiane.familymoneytrackerapi.domain.CategoriaReceita;
import com.cristhiane.familymoneytrackerapi.domain.Role;
import com.cristhiane.familymoneytrackerapi.domain.User;
import com.cristhiane.familymoneytrackerapi.enums.TipoTransacao;
import com.cristhiane.familymoneytrackerapi.enums.TipoUsuario;
import com.cristhiane.familymoneytrackerapi.repository.CategoriaTransacaoRepository;
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
	CategoriaTransacaoRepository categoriaTransacaoRepository;
	
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
		CategoriaDespesa supermercado = new CategoriaDespesa(null, "Supermercado", "Gastos com supermercado", TipoTransacao.DESPESA, 5000);
		CategoriaDespesa saude = new CategoriaDespesa(null, "Saúde", "Gastos com remédios e consultas", TipoTransacao.DESPESA, 2000);
		CategoriaDespesa lazer = new CategoriaDespesa(null, "Lazer", "Entretenimento, passeios, viagens, etc", TipoTransacao.DESPESA, 1000);
		CategoriaDespesa contas = new CategoriaDespesa(null, "Contas", "Contas fixas, como água, telefone, internet, etc", TipoTransacao.DESPESA, 2500);
		CategoriaDespesa transporte = new CategoriaDespesa(null, "Transporte", "Gasolina, corridas por aplicativo, etc", TipoTransacao.DESPESA, 1200);
		CategoriaDespesa vestuario = new CategoriaDespesa(null, "Vestuário", "Gastos com roupas", TipoTransacao.DESPESA, 500);
		CategoriaDespesa artigosLar = new CategoriaDespesa(null, "Artigos para o lar", "Artigos de cama, mesa e banho", TipoTransacao.DESPESA, 700);
		
		// Salvando categorias de despesas no banco de dados
		categoriaTransacaoRepository.saveAll(Arrays.asList(supermercado, saude, lazer, contas, transporte, vestuario, artigosLar));
		
		//-------------------------------------------------------------------------
		// Inserindo categorias de receitas
		CategoriaReceita salario = new CategoriaReceita(null, "Salário", "Salário do mês", TipoTransacao.RECEITA);
		CategoriaReceita rendaExtra = new CategoriaReceita(null, "Renda Extra", "Ganhos além do salário mensal", TipoTransacao.RECEITA);
		CategoriaReceita rendimentoInvestimento = new CategoriaReceita(null, "Rendimento investimento", "Rendimentos provenientes de investimentos", TipoTransacao.RECEITA);
		
		// Salvando categorias de receitas no banco de dados
		categoriaTransacaoRepository.saveAll(Arrays.asList(salario, rendaExtra, rendimentoInvestimento));
	}
	
}
