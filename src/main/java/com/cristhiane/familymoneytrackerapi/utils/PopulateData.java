package com.cristhiane.familymoneytrackerapi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cristhiane.familymoneytrackerapi.domain.CartaoDeCredito;
import com.cristhiane.familymoneytrackerapi.domain.CategoriaDespesa;
import com.cristhiane.familymoneytrackerapi.domain.CategoriaReceita;
import com.cristhiane.familymoneytrackerapi.domain.Conta;
import com.cristhiane.familymoneytrackerapi.domain.DespesaCredito;
import com.cristhiane.familymoneytrackerapi.domain.DespesaDebitoDinheiro;
import com.cristhiane.familymoneytrackerapi.domain.DespesaFinanciamentoEmprestimo;
import com.cristhiane.familymoneytrackerapi.domain.GrupoUsuarios;
import com.cristhiane.familymoneytrackerapi.domain.Receita;
import com.cristhiane.familymoneytrackerapi.domain.Role;
import com.cristhiane.familymoneytrackerapi.domain.User;
import com.cristhiane.familymoneytrackerapi.enums.Banco;
import com.cristhiane.familymoneytrackerapi.enums.BandeiraCartaoDeCredito;
import com.cristhiane.familymoneytrackerapi.enums.FormaDePagamento;
import com.cristhiane.familymoneytrackerapi.enums.TipoConta;
import com.cristhiane.familymoneytrackerapi.enums.TipoUsuario;
import com.cristhiane.familymoneytrackerapi.repository.CartaoDeCreditoRepository;
import com.cristhiane.familymoneytrackerapi.repository.CategoriaDespesaRepository;
import com.cristhiane.familymoneytrackerapi.repository.CategoriaReceitaRepository;
import com.cristhiane.familymoneytrackerapi.repository.ContaRepository;
import com.cristhiane.familymoneytrackerapi.repository.DespesaCreditoRepository;
import com.cristhiane.familymoneytrackerapi.repository.DespesaDebitoDinheiroRepository;
import com.cristhiane.familymoneytrackerapi.repository.DespesaFinanciamentoEmprestimoRepository;
import com.cristhiane.familymoneytrackerapi.repository.GrupoUsuariosRepository;
import com.cristhiane.familymoneytrackerapi.repository.ReceitaRepository;
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
	
	@Autowired
	ReceitaRepository receitaRepository;
	
	@Autowired
	DespesaDebitoDinheiroRepository despesaDebitoDinheiroRepository;
	
	@Autowired
	DespesaCreditoRepository despesaCreditoRepository;
	
	@Autowired
	DespesaFinanciamentoEmprestimoRepository despesaFinanciamentoEmprestimoRepository;
	
	@Autowired
	GrupoUsuariosRepository grupoUsuariosRepository;
	
	@PostConstruct
	public void insertData() throws ParseException {
		// Inserindo grupo de usuários
		GrupoUsuarios aGrandeFamilia = new GrupoUsuarios(null, "A Grande Família", "aGrandeFamilia");
		GrupoUsuarios adminSistema = new GrupoUsuarios(null, "Admins do Sistema", "adminsDoSistema");
		
		// Inserindo perfis de usuário
		Role role_group_user = new Role(TipoUsuario.USUARIO_COMUM);
		Role role_group_admin = new Role(TipoUsuario.ADMIN_GRUPO);
		Role role_system_admin = new Role(TipoUsuario.ADMIN_SISTEMA);

		// Inserindo usuários
		User group_admin = new User("Admin de grupo", "group_admin@teste.com", encoder.encode("Gr0up@adm1n_T3st3"));
		group_admin.getRoles().add(role_group_admin);
		group_admin.setGrupoUsuarios(aGrandeFamilia);
		
		User group_user = new User("Usuário de grupo", "group_user@teste.com", encoder.encode("Gr0upUs3r_T3st3"));
		group_user.getRoles().add(role_group_user);
		group_user.setGrupoUsuarios(aGrandeFamilia);
		
		User system_admin = new User("Admin do sistema", "system_admin@teste.com", encoder.encode("Syst3m@dm1n_T3st3"));
		system_admin.getRoles().add(role_system_admin);
		system_admin.setGrupoUsuarios(adminSistema);
		
		// Salvando grupo, perfis e usuários no banco de dados
		grupoUsuariosRepository.saveAll(Arrays.asList(aGrandeFamilia, adminSistema));
		roleRepository.saveAll(Arrays.asList(role_group_user, role_group_admin, role_system_admin));
		userRepository.saveAll(Arrays.asList(group_admin, group_user, system_admin));
		
		//-------------------------------------------------------------------------
		// Inserindo categorias de despesas
		CategoriaDespesa supermercado = new CategoriaDespesa(null, "Supermercado", "Gastos com supermercado", 5000, group_admin);
		CategoriaDespesa refeicoes = new CategoriaDespesa(null, "Refeições", "Refeições fora de casa", 1500, group_admin);
		CategoriaDespesa saude = new CategoriaDespesa(null, "Saúde", "Gastos com remédios e consultas", 2000, group_admin);
		CategoriaDespesa lazer = new CategoriaDespesa(null, "Lazer", "Entretenimento, passeios, viagens, etc", 1000, group_admin);
		CategoriaDespesa contas = new CategoriaDespesa(null, "Contas", "Contas fixas, como água, telefone, internet, etc", 2500, group_admin);
		CategoriaDespesa transporte = new CategoriaDespesa(null, "Transporte", "Gasolina, corridas por aplicativo, etc", 1200, group_admin);
		CategoriaDespesa vestuario = new CategoriaDespesa(null, "Vestuário", "Gastos com roupas", 500, group_admin);
		CategoriaDespesa artigosLar = new CategoriaDespesa(null, "Artigos para o lar", "Artigos de cama, mesa e banho", 700, group_admin);
		
		// Salvando categorias de despesas no banco de dados
		categoriaDespesaRepository.saveAll(Arrays.asList(supermercado, refeicoes, saude, lazer, contas, transporte, vestuario, artigosLar));
		
		//-------------------------------------------------------------------------
		// Inserindo categorias de receitas
		CategoriaReceita salario = new CategoriaReceita(null, "Salário", "Salário do mês", group_admin);
		CategoriaReceita rendaExtra = new CategoriaReceita(null, "Renda Extra", "Ganhos além do salário mensal", group_admin);
		CategoriaReceita rendimentoInvestimento = new CategoriaReceita(null, "Rendimento investimento", "Rendimentos provenientes de investimentos", group_admin);
		
		// Salvando categorias de receitas no banco de dados
		categoriaReceitaRepository.saveAll(Arrays.asList(salario, rendaExtra, rendimentoInvestimento));
		
		//-------------------------------------------------------------------------
		// Inserindo contas
		Conta conta_familia = new Conta(null, "Conta da família", 5000, TipoConta.CONTA_CORRENTE, group_admin);
		Conta poupanca_familia = new Conta(null, "Poupança da família", 10000, TipoConta.CONTA_POUPANCA, group_admin);
		Conta dinheiro = new Conta(null, "Dinheiro em casa", 150, TipoConta.DINHEIRO, group_admin);
		Conta mesada = new Conta(null, "Mesada das crianças", 75, TipoConta.MESADA, group_admin);
		
		contaRepository.saveAll(Arrays.asList(conta_familia, poupanca_familia, dinheiro, mesada));
		
		//-------------------------------------------------------------------------
		// Inserindo cartões de crédito
		CartaoDeCredito nubank = new CartaoDeCredito(null, "Nubank", BandeiraCartaoDeCredito.MASTERCARD, 5000, 13, 20, group_admin);
		CartaoDeCredito bb = new CartaoDeCredito(null, "Banco do Brasil", BandeiraCartaoDeCredito.VISA, 2500, 11, 17, group_admin);
		CartaoDeCredito santander = new CartaoDeCredito(null, "Santander", BandeiraCartaoDeCredito.ELO, 3200, 15, 25, group_admin);
		
		cartaoDeCreditoRepository.saveAll(Arrays.asList(nubank, bb, santander));
		
		//-------------------------------------------------------------------------
		// Inserindo receitas
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Receita salarioMesMarcoPai = new Receita(null, 1900, "Salário do mês de Março - pai", sdf.parse("14/03/2022"), false, null, conta_familia, salario, group_admin);
		Receita salarioMesMarcoMae = new Receita(null, 2000, "Salário do mês de Março - mãe", sdf.parse("09/03/2022"), false, null, conta_familia, salario, group_admin);
		Receita salarioMesMarcoTio = new Receita(null, 2100, "Salário do mês de Março - tio", sdf.parse("07/03/2022"), false, null, conta_familia, salario, group_admin);
		Receita mesadaMesMarco = new Receita(null, 145, "Mesada do mês de Março", sdf.parse("10/03/2022"), false, null, mesada, rendaExtra, group_admin);
		Receita rendimentosMesMarco = new Receita(null, 65, "Rendimentos investimentos do mês de Março", sdf.parse("09/03/2022"), false, null, poupanca_familia, rendimentoInvestimento, group_admin);
		
		Receita salarioMesAbrilPai = new Receita(null, 2000, "Salário do mês de Abril - pai", sdf.parse("15/04/2022"), false, null, conta_familia, salario, group_admin);
		Receita salarioMesAbrilMae = new Receita(null, 2100, "Salário do mês de Abril - mãe", sdf.parse("10/04/2022"), false, null, conta_familia, salario, group_admin);
		Receita salarioMesAbrilTio = new Receita(null, 2200, "Salário do mês de Abril - tio", sdf.parse("08/04/2022"), false, null, conta_familia, salario, group_admin);
		Receita mesadaMesAbril = new Receita(null, 150, "Mesada do mês de Abril", sdf.parse("11/04/2022"), false, null, mesada, rendaExtra, group_admin);
		Receita rendimentosMesAbril = new Receita(null, 70, "Rendimentos investimentos do mês de Abril", sdf.parse("08/04/2022"), false, null, poupanca_familia, rendimentoInvestimento, group_admin);
		
		Receita salarioMesMaioPai = new Receita(null, 2541, "Salário do mês de Maio - pai", sdf.parse("15/05/2022"), false, null, conta_familia, salario, group_admin);
		Receita salarioMesMaioMae = new Receita(null, 2654, "Salário do mês de Maio - mãe", sdf.parse("10/05/2022"), false, null, conta_familia, salario, group_admin);
		Receita salarioMesMaioTio = new Receita(null, 2478, "Salário do mês de Maio - tio", sdf.parse("08/05/2022"), false, null, conta_familia, salario, group_admin);
		Receita mesadaMesMaio = new Receita(null, 157, "Mesada do mês de Maio", sdf.parse("11/05/2022"), false, null, mesada, rendaExtra, group_admin);
		Receita rendimentosMesMaio = new Receita(null, 7096, "Rendimentos investimentos do mês de Maio", sdf.parse("08/05/2022"), false, null, poupanca_familia, rendimentoInvestimento, group_admin);
		
		Receita salarioMesJunhoPai = new Receita(null, 2547, "Salário do mês de Junho - pai", sdf.parse("15/06/2022"), false, null, conta_familia, salario, group_admin);
		Receita salarioMesJunhoMae = new Receita(null, 2698, "Salário do mês de Junho - mãe", sdf.parse("10/06/2022"), false, null, conta_familia, salario, group_admin);
		Receita salarioMesJunhoTio = new Receita(null, 2874, "Salário do mês de Junho - tio", sdf.parse("08/06/2022"), false, null, conta_familia, salario, group_admin);
		Receita mesadaMesJunho = new Receita(null, 165, "Mesada do mês de Junho", sdf.parse("11/06/2022"), false, null, mesada, rendaExtra, group_admin);
		Receita rendimentosMesJunho = new Receita(null, 54, "Rendimentos investimentos do mês de Junho", sdf.parse("08/06/2022"), false, null, poupanca_familia, rendimentoInvestimento, group_admin);
		
		Receita salarioMesJulhoPai = new Receita(null, 2687, "Salário do mês de Julho - pai", sdf.parse("15/07/2022"), false, null, conta_familia, salario, group_admin);
		Receita salarioMesJulhoMae = new Receita(null, 2458, "Salário do mês de Julho - mãe", sdf.parse("10/07/2022"), false, null, conta_familia, salario, group_admin);
		Receita salarioMesJulhoTio = new Receita(null, 2965, "Salário do mês de Julho - tio", sdf.parse("08/07/2022"), false, null, conta_familia, salario, group_admin);
		Receita mesadaMesJulho = new Receita(null, 178, "Mesada do mês de Julho", sdf.parse("11/07/2022"), false, null, mesada, rendaExtra, group_admin);
		Receita rendimentosMesJulho = new Receita(null, 68, "Rendimentos investimentos do mês de Julho", sdf.parse("08/07/2022"), false, null, poupanca_familia, rendimentoInvestimento, group_admin);
		
		receitaRepository.saveAll(Arrays.asList(salarioMesMarcoPai, salarioMesMarcoMae, salarioMesMarcoTio, mesadaMesMarco, rendimentosMesMarco, salarioMesAbrilPai, salarioMesAbrilMae, salarioMesAbrilTio, mesadaMesAbril, rendimentosMesAbril, salarioMesMaioPai, salarioMesMaioMae, salarioMesMaioTio, mesadaMesMaio, rendimentosMesMaio, salarioMesJunhoPai, salarioMesJunhoMae, salarioMesJunhoTio, mesadaMesJunho, rendimentosMesJunho, salarioMesJulhoPai, salarioMesJulhoMae, salarioMesJulhoTio, mesadaMesJulho, rendimentosMesJulho));
		
		//-------------------------------------------------------------------------
		// Inserindo despesas em débito e dinheiro
		DespesaDebitoDinheiro almoco_marco = new DespesaDebitoDinheiro(null, 57, "Almoço do dia 09/03", sdf.parse("09/03/2022"), false, null, FormaDePagamento.DEBITO, refeicoes, group_admin, conta_familia );
		DespesaDebitoDinheiro jantar_marco = new DespesaDebitoDinheiro(null, 46, "Jantar do dia 10/03", sdf.parse("10/03/2022"), false, null, FormaDePagamento.DINHEIRO, refeicoes, group_admin, dinheiro);
		DespesaDebitoDinheiro lanche_tarde_marco = new DespesaDebitoDinheiro(null, 18, "Lanche da tarde do dia 02/03", sdf.parse("02/03/2022"), false, null, FormaDePagamento.DINHEIRO, refeicoes, group_admin, mesada);
		DespesaDebitoDinheiro supermercado_compras_marco = new DespesaDebitoDinheiro(null, 400, "Compras no supermercado - 12/03", sdf.parse("12/03/2022"), false, null, FormaDePagamento.DEBITO, supermercado, group_admin, conta_familia);
		
		DespesaDebitoDinheiro almoco = new DespesaDebitoDinheiro(null, 50, "Almoço do dia 08/04", sdf.parse("08/04/2022"), false, null, FormaDePagamento.DEBITO, refeicoes, group_admin, conta_familia );
		DespesaDebitoDinheiro jantar = new DespesaDebitoDinheiro(null, 45, "Jantar do dia 07/04", sdf.parse("07/04/2022"), false, null, FormaDePagamento.DINHEIRO, refeicoes, group_admin, conta_familia);
		DespesaDebitoDinheiro lanche_tarde = new DespesaDebitoDinheiro(null, 15, "Lanche da tarde do dia 06/04", sdf.parse("06/04/2022"), false, null, FormaDePagamento.DINHEIRO, refeicoes, group_admin, mesada);
		DespesaDebitoDinheiro supermercado_compras = new DespesaDebitoDinheiro(null, 250, "Compras no supermercado - 05/04", sdf.parse("05/04/2022"), false, null, FormaDePagamento.DEBITO, supermercado, group_admin, conta_familia);
		
		despesaDebitoDinheiroRepository.saveAll(Arrays.asList(almoco_marco, jantar_marco, lanche_tarde_marco, supermercado_compras_marco, almoco, jantar, lanche_tarde, supermercado_compras));

		//-------------------------------------------------------------------------
		// Inserindo despesas no crédito
		DespesaCredito televisao = new DespesaCredito(null, 5000, "Televisão para a sala", sdf.parse("25/03/2022"), false, null, FormaDePagamento.CARTAO_DE_CREDITO, artigosLar, group_admin, 24, nubank );
		DespesaCredito roupeiro = new DespesaCredito(null, 1800, "Roupeiro para o quarto das crianças", sdf.parse("13/03/2022"), false, null, FormaDePagamento.CARTAO_DE_CREDITO, artigosLar, group_admin, 10, bb );
		DespesaCredito roupaCama = new DespesaCredito(null, 1400, "Exames de rotina das crianças", sdf.parse("18/03/2022"), false, null, FormaDePagamento.CARTAO_DE_CREDITO, saude, group_admin, 6, santander );
		
		DespesaCredito maquinaLavar = new DespesaCredito(null, 2500, "Máquina de lavar roupa", sdf.parse("02/04/2022"), false, null, FormaDePagamento.CARTAO_DE_CREDITO, artigosLar, group_admin, 12, nubank );
		DespesaCredito viagemPraia = new DespesaCredito(null, 5000, "Viagem para a praia", sdf.parse("15/04/2022"), false, null, FormaDePagamento.CARTAO_DE_CREDITO, lazer, group_admin, 24, bb );
		DespesaCredito roupas = new DespesaCredito(null, 1200, "Roupas para as crianças", sdf.parse("07/04/2022"), false, null, FormaDePagamento.CARTAO_DE_CREDITO, vestuario, group_admin, 15, santander );
		
		despesaCreditoRepository.saveAll(Arrays.asList(televisao, roupeiro, roupaCama, maquinaLavar, viagemPraia, roupas));
		
		//-------------------------------------------------------------------------
		// Inserindo despesas de empréstimos e financiamentos
		DespesaFinanciamentoEmprestimo motoPai = new DespesaFinanciamentoEmprestimo(null, 8000, "Compra da moto do pai", sdf.parse("02/03/2022"), false, null, FormaDePagamento.FINANCIAMENTO, transporte, group_user, 36, Banco.BANCO_DO_BRASIL);
		DespesaFinanciamentoEmprestimo quitarDividas = new DespesaFinanciamentoEmprestimo(null, 2000, "Empréstimo para quitar dívidas", sdf.parse("01/03/2022"), false, null, FormaDePagamento.EMPRESTIMO, contas, group_user, 12, Banco.ITAU);
		
		DespesaFinanciamentoEmprestimo carro = new DespesaFinanciamentoEmprestimo(null, 40000, "Compra do carro da família", sdf.parse("01/04/2022"), false, null, FormaDePagamento.FINANCIAMENTO, transporte, group_user, 48, Banco.BANCO_DO_BRASIL);
		DespesaFinanciamentoEmprestimo reforma = new DespesaFinanciamentoEmprestimo(null, 15000, "Reforma da sala", sdf.parse("01/03/2022"), false, null, FormaDePagamento.EMPRESTIMO, artigosLar, group_user, 24, Banco.ITAU);
		
		despesaFinanciamentoEmprestimoRepository.saveAll(Arrays.asList(motoPai, quitarDividas, carro, reforma));
	}
	
}
