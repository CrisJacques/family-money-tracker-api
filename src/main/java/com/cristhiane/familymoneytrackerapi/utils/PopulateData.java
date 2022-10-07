package com.cristhiane.familymoneytrackerapi.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

/**
 * Classe para popular o banco de dados com vários exemplos de registros de
 * entidades (cartões de crédito, contas, despesas, receitas, etc) para
 * demonstrações e testes do sistema
 *
 */
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

	/**
	 * Insere dados no banco de dados para testes e demonstrações do sistema
	 */
	//Descomentar a linha abaixo caso queira que os dados de teste sejam populados ao subir a aplicação
	@PostConstruct
	public void insertData() {
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

		// -------------------------------------------------------------------------
		// Inserindo categorias de despesas
		CategoriaDespesa alimentacao = new CategoriaDespesa(null, "Alimentação", "Gastos com supermercado e refeições",
				5000, group_admin);
		CategoriaDespesa saude = new CategoriaDespesa(null, "Saúde", "Gastos com remédios e consultas", 2000,
				group_admin);
		CategoriaDespesa lazer = new CategoriaDespesa(null, "Lazer", "Entretenimento, passeios, viagens, etc", 1000,
				group_admin);
		CategoriaDespesa contas = new CategoriaDespesa(null, "Contas",
				"Contas fixas, como água, telefone, internet, etc", 2500, group_admin);
		CategoriaDespesa transporte = new CategoriaDespesa(null, "Transporte", "Gasolina, corridas por aplicativo, etc",
				1200, group_admin);
		CategoriaDespesa vestuario = new CategoriaDespesa(null, "Vestuário", "Gastos com roupas", 500, group_admin);

		// Salvando categorias de despesas no banco de dados
		categoriaDespesaRepository.saveAll(Arrays.asList(alimentacao, saude, lazer, contas, transporte, vestuario));

		// -------------------------------------------------------------------------
		// Inserindo categorias de receitas
		CategoriaReceita salario = new CategoriaReceita(null, "Salário", "Salário do mês", group_admin);
		CategoriaReceita rendaExtra = new CategoriaReceita(null, "Renda Extra", "Ganhos além do salário mensal",
				group_admin);
		CategoriaReceita rendimentoInvestimento = new CategoriaReceita(null, "Rendimento investimento",
				"Rendimentos provenientes de investimentos", group_admin);

		// Salvando categorias de receitas no banco de dados
		categoriaReceitaRepository.saveAll(Arrays.asList(salario, rendaExtra, rendimentoInvestimento));

		// -------------------------------------------------------------------------
		// Inserindo contas
		Conta conta_familia = new Conta(null, "Conta da família", 5000, TipoConta.CONTA_CORRENTE, group_admin);
		Conta poupanca_familia = new Conta(null, "Poupança da família", 10000, TipoConta.CONTA_POUPANCA, group_admin);
		Conta dinheiro = new Conta(null, "Dinheiro em casa", 150, TipoConta.DINHEIRO, group_admin);
		Conta mesada = new Conta(null, "Mesada das crianças", 75, TipoConta.MESADA, group_admin);

		// Salvando contas no banco de dados
		contaRepository.saveAll(Arrays.asList(conta_familia, poupanca_familia, dinheiro, mesada));

		// -------------------------------------------------------------------------
		// Inserindo cartões de crédito
		CartaoDeCredito nubank = new CartaoDeCredito(null, "Nubank", BandeiraCartaoDeCredito.MASTERCARD, 5000, 13, 20,
				group_admin);
		CartaoDeCredito bb = new CartaoDeCredito(null, "Banco do Brasil", BandeiraCartaoDeCredito.VISA, 2500, 11, 17,
				group_admin);
		CartaoDeCredito santander = new CartaoDeCredito(null, "Santander", BandeiraCartaoDeCredito.ELO, 3200, 15, 25,
				group_admin);

		// Salvando cartões de crédito no banco de dados
		cartaoDeCreditoRepository.saveAll(Arrays.asList(nubank, bb, santander));

		// -------------------------------------------------------------------------
		// Inserindo receitas
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		Receita salarioMesMarcoPai = new Receita(null, 1900, "Salário do mês de Setembro - pai",
				LocalDate.parse("14/09/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita salarioMesMarcoMae = new Receita(null, 2000, "Salário do mês de Setembro - mãe",
				LocalDate.parse("09/09/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita salarioMesMarcoTio = new Receita(null, 2100, "Salário do mês de Setembro - tio",
				LocalDate.parse("07/09/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita mesadaMesMarco = new Receita(null, 245, "Mesada do mês de Setembro",
				LocalDate.parse("10/09/2022", formatter), false, null, mesada, rendaExtra, group_admin);
		Receita rendimentosMesMarco = new Receita(null, 65, "Rendimentos investimentos do mês de Setembro",
				LocalDate.parse("09/09/2022", formatter), false, null, poupanca_familia, rendimentoInvestimento,
				group_admin);

		Receita salarioMesAbrilPai = new Receita(null, 2000, "Salário do mês de Outubro - pai",
				LocalDate.parse("15/10/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita salarioMesAbrilMae = new Receita(null, 2100, "Salário do mês de Outubro - mãe",
				LocalDate.parse("10/10/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita salarioMesAbrilTio = new Receita(null, 2200, "Salário do mês de Outubro - tio",
				LocalDate.parse("08/10/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita mesadaMesAbril = new Receita(null, 350, "Mesada do mês de Outubro",
				LocalDate.parse("11/10/2022", formatter), false, null, mesada, rendaExtra, group_admin);
		Receita rendimentosMesAbril = new Receita(null, 70, "Rendimentos investimentos do mês de Outubro",
				LocalDate.parse("08/10/2022", formatter), false, null, poupanca_familia, rendimentoInvestimento,
				group_admin);

		Receita salarioMesMaioPai = new Receita(null, 2541, "Salário do mês de Novembro - pai",
				LocalDate.parse("15/11/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita salarioMesMaioMae = new Receita(null, 2654, "Salário do mês de Novembro - mãe",
				LocalDate.parse("10/11/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita salarioMesMaioTio = new Receita(null, 2478, "Salário do mês de Novembro - tio",
				LocalDate.parse("08/11/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita mesadaMesMaio = new Receita(null, 298, "Mesada do mês de Novembro",
				LocalDate.parse("11/11/2022", formatter), false, null, mesada, rendaExtra, group_admin);
		Receita rendimentosMesMaio = new Receita(null, 71, "Rendimentos investimentos do mês de Novembro",
				LocalDate.parse("08/11/2022", formatter), false, null, poupanca_familia, rendimentoInvestimento,
				group_admin);

		Receita salarioMesJunhoPai = new Receita(null, 2547, "Salário do mês de Dezembro - pai",
				LocalDate.parse("15/12/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita salarioMesJunhoMae = new Receita(null, 2698, "Salário do mês de Dezembro - mãe",
				LocalDate.parse("10/12/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita salarioMesJunhoTio = new Receita(null, 2874, "Salário do mês de Dezembro - tio",
				LocalDate.parse("08/12/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita mesadaMesJunho = new Receita(null, 244, "Mesada do mês de Dezembro",
				LocalDate.parse("11/12/2022", formatter), false, null, mesada, rendaExtra, group_admin);
		Receita rendimentosMesJunho = new Receita(null, 54, "Rendimentos investimentos do mês de Dezembro",
				LocalDate.parse("08/12/2022", formatter), false, null, poupanca_familia, rendimentoInvestimento,
				group_admin);

		Receita salarioMesJulhoPai = new Receita(null, 2687, "Salário do mês de Janeiro - pai",
				LocalDate.parse("15/01/2023", formatter), false, null, conta_familia, salario, group_admin);
		Receita salarioMesJulhoMae = new Receita(null, 2458, "Salário do mês de Janeiro - mãe",
				LocalDate.parse("10/01/2023", formatter), false, null, conta_familia, salario, group_admin);
		Receita salarioMesJulhoTio = new Receita(null, 2965, "Salário do mês de Janeiro - tio",
				LocalDate.parse("08/01/2023", formatter), false, null, conta_familia, salario, group_admin);
		Receita mesadaMesJulho = new Receita(null, 298, "Mesada do mês de Janeiro",
				LocalDate.parse("11/01/2023", formatter), false, null, mesada, rendaExtra, group_admin);
		Receita rendimentosMesJulho = new Receita(null, 68, "Rendimentos investimentos do mês de Janeiro",
				LocalDate.parse("08/01/2023", formatter), false, null, poupanca_familia, rendimentoInvestimento,
				group_admin);

		// Salvando receitas no banco de dados
		receitaRepository.saveAll(Arrays.asList(salarioMesMarcoPai, salarioMesMarcoMae, salarioMesMarcoTio,
				mesadaMesMarco, rendimentosMesMarco, salarioMesAbrilPai, salarioMesAbrilMae, salarioMesAbrilTio,
				mesadaMesAbril, rendimentosMesAbril, salarioMesMaioPai, salarioMesMaioMae, salarioMesMaioTio,
				mesadaMesMaio, rendimentosMesMaio, salarioMesJunhoPai, salarioMesJunhoMae, salarioMesJunhoTio,
				mesadaMesJunho, rendimentosMesJunho, salarioMesJulhoPai, salarioMesJulhoMae, salarioMesJulhoTio,
				mesadaMesJulho, rendimentosMesJulho));

		// -------------------------------------------------------------------------
		// Inserindo despesas em débito e dinheiro
		DespesaDebitoDinheiro almoco_setembro = new DespesaDebitoDinheiro(null, 57, "Almoço do dia 09/09",
				LocalDate.parse("09/09/2022", formatter), false, null, FormaDePagamento.DEBITO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro jantar_setembro = new DespesaDebitoDinheiro(null, 46, "Jantar do dia 10/09",
				LocalDate.parse("10/09/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, dinheiro);
		DespesaDebitoDinheiro lanche_tarde_setembro = new DespesaDebitoDinheiro(null, 18, "Lanche da tarde do dia 02/09",
				LocalDate.parse("02/09/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, mesada);
		DespesaDebitoDinheiro supermercado_compras_setembro = new DespesaDebitoDinheiro(null, 400,
				"Compras no supermercado - 12/09", LocalDate.parse("12/09/2022", formatter), false, null,
				FormaDePagamento.DEBITO, alimentacao, group_admin, conta_familia);

		DespesaDebitoDinheiro almoco = new DespesaDebitoDinheiro(null, 50, "Almoço do dia 08/10",
				LocalDate.parse("08/10/2022", formatter), false, null, FormaDePagamento.DEBITO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro jantar = new DespesaDebitoDinheiro(null, 45, "Jantar do dia 07/10",
				LocalDate.parse("07/10/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro lanche_tarde = new DespesaDebitoDinheiro(null, 15, "Lanche da tarde do dia 06/10",
				LocalDate.parse("06/10/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, mesada);
		DespesaDebitoDinheiro supermercado_compras = new DespesaDebitoDinheiro(null, 250,
				"Compras no supermercado - 05/10", LocalDate.parse("05/10/2022", formatter), false, null,
				FormaDePagamento.DEBITO, alimentacao, group_admin, conta_familia);

		DespesaDebitoDinheiro almoco1_novembro = new DespesaDebitoDinheiro(null, 47, "Almoço do dia 16/11",
				LocalDate.parse("16/11/2022", formatter), false, null, FormaDePagamento.DEBITO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro jantar1_novembro = new DespesaDebitoDinheiro(null, 36, "Jantar do dia 03/11",
				LocalDate.parse("03/11/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, dinheiro);
		DespesaDebitoDinheiro lanche_tarde1_novembro = new DespesaDebitoDinheiro(null, 13, "Lanche da tarde do dia 04/11",
				LocalDate.parse("04/11/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, mesada);
		DespesaDebitoDinheiro supermercado_compras1_novembro = new DespesaDebitoDinheiro(null, 369,
				"Compras no supermercado - 02/11", LocalDate.parse("02/11/2022", formatter), false, null,
				FormaDePagamento.DEBITO, alimentacao, group_admin, conta_familia);
		DespesaDebitoDinheiro almoco2_novembro = new DespesaDebitoDinheiro(null, 41, "Almoço do dia 11/11",
				LocalDate.parse("11/11/2022", formatter), false, null, FormaDePagamento.DEBITO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro jantar2_novembro = new DespesaDebitoDinheiro(null, 32, "Jantar do dia 20/11",
				LocalDate.parse("20/11/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro lanche_tarde2_novembro = new DespesaDebitoDinheiro(null, 14, "Lanche da tarde do dia 08/11",
				LocalDate.parse("08/11/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, mesada);
		DespesaDebitoDinheiro supermercado_compras_novembro = new DespesaDebitoDinheiro(null, 250,
				"Compras no supermercado - 03/11", LocalDate.parse("03/11/2022", formatter), false, null,
				FormaDePagamento.DEBITO, alimentacao, group_admin, conta_familia);
		DespesaDebitoDinheiro luz_novembro = new DespesaDebitoDinheiro(null, 178, "Conta de Luz",
				LocalDate.parse("09/11/2022", formatter), false, null, FormaDePagamento.DEBITO, contas, group_admin,
				conta_familia);
		DespesaDebitoDinheiro cond_novembro = new DespesaDebitoDinheiro(null, 567, "Boleto Condomínio",
				LocalDate.parse("22/11/2022", formatter), false, null, FormaDePagamento.DEBITO, contas, group_admin,
				conta_familia);
		DespesaDebitoDinheiro internet_novembro = new DespesaDebitoDinheiro(null, 178, "Fatura Telefone e Internet",
				LocalDate.parse("17/11/2022", formatter), false, null, FormaDePagamento.DEBITO, contas, group_admin,
				conta_familia);

		DespesaDebitoDinheiro almoco1_dezembro = new DespesaDebitoDinheiro(null, 47, "Almoço do dia 16/12",
				LocalDate.parse("16/12/2022", formatter), false, null, FormaDePagamento.DEBITO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro jantar1_dezembro = new DespesaDebitoDinheiro(null, 36, "Jantar do dia 03/12",
				LocalDate.parse("03/12/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, dinheiro);
		DespesaDebitoDinheiro lanche_tarde1_dezembro = new DespesaDebitoDinheiro(null, 13, "Lanche da tarde do dia 04/12",
				LocalDate.parse("04/12/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, mesada);
		DespesaDebitoDinheiro supermercado_compras1_dezembro = new DespesaDebitoDinheiro(null, 369,
				"Compras no supermercado - 02/12", LocalDate.parse("02/12/2022", formatter), false, null,
				FormaDePagamento.DEBITO, alimentacao, group_admin, conta_familia);
		DespesaDebitoDinheiro almoco2_dezembro = new DespesaDebitoDinheiro(null, 41, "Almoço do dia 11/12",
				LocalDate.parse("11/12/2022", formatter), false, null, FormaDePagamento.DEBITO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro jantar2_dezembro = new DespesaDebitoDinheiro(null, 32, "Jantar do dia 20/12",
				LocalDate.parse("20/12/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro lanche_tarde2_dezembro = new DespesaDebitoDinheiro(null, 14, "Lanche da tarde do dia 08/12",
				LocalDate.parse("08/12/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, mesada);
		DespesaDebitoDinheiro supermercado_compras_dezembro = new DespesaDebitoDinheiro(null, 250,
				"Compras no supermercado - 03/12", LocalDate.parse("03/12/2022", formatter), false, null,
				FormaDePagamento.DEBITO, alimentacao, group_admin, conta_familia);
		DespesaDebitoDinheiro luz_dezembro = new DespesaDebitoDinheiro(null, 178, "Conta de Luz",
				LocalDate.parse("09/12/2022", formatter), false, null, FormaDePagamento.DEBITO, contas, group_admin,
				conta_familia);
		DespesaDebitoDinheiro cond_dezembro = new DespesaDebitoDinheiro(null, 567, "Boleto Condomínio",
				LocalDate.parse("22/12/2022", formatter), false, null, FormaDePagamento.DEBITO, contas, group_admin,
				conta_familia);
		DespesaDebitoDinheiro internet_dezembro = new DespesaDebitoDinheiro(null, 178, "Fatura Telefone e Internet",
				LocalDate.parse("17/12/2022", formatter), false, null, FormaDePagamento.DEBITO, contas, group_admin,
				conta_familia);

		DespesaDebitoDinheiro almoco1_janeiro = new DespesaDebitoDinheiro(null, 47, "Almoço do dia 16/01",
				LocalDate.parse("16/01/2023", formatter), false, null, FormaDePagamento.DEBITO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro jantar1_janeiro = new DespesaDebitoDinheiro(null, 36, "Jantar do dia 03/01",
				LocalDate.parse("03/01/2023", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, dinheiro);
		DespesaDebitoDinheiro lanche_tarde1_janeiro = new DespesaDebitoDinheiro(null, 13, "Lanche da tarde do dia 04/01",
				LocalDate.parse("04/01/2023", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, mesada);
		DespesaDebitoDinheiro supermercado_compras1_janeiro = new DespesaDebitoDinheiro(null, 369,
				"Compras no supermercado - 02/01", LocalDate.parse("02/01/2023", formatter), false, null,
				FormaDePagamento.DEBITO, alimentacao, group_admin, conta_familia);
		DespesaDebitoDinheiro almoco2_janeiro = new DespesaDebitoDinheiro(null, 41, "Almoço do dia 11/01",
				LocalDate.parse("11/01/2023", formatter), false, null, FormaDePagamento.DEBITO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro jantar2_janeiro = new DespesaDebitoDinheiro(null, 32, "Jantar do dia 20/01",
				LocalDate.parse("20/01/2023", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro lanche_tarde2_janeiro = new DespesaDebitoDinheiro(null, 250,
				"Compras no supermercado - 03/01", LocalDate.parse("03/01/2023", formatter), false, null,
				FormaDePagamento.DEBITO, alimentacao, group_admin, conta_familia);
		DespesaDebitoDinheiro luz_janeiro = new DespesaDebitoDinheiro(null, 178, "Conta de Luz",
				LocalDate.parse("09/01/2023", formatter), false, null, FormaDePagamento.DEBITO, contas, group_admin,
				conta_familia);
		DespesaDebitoDinheiro cond_janeiro = new DespesaDebitoDinheiro(null, 567, "Boleto Condomínio",
				LocalDate.parse("22/01/2023", formatter), false, null, FormaDePagamento.DEBITO, contas, group_admin,
				conta_familia);
		DespesaDebitoDinheiro internet_janeiro = new DespesaDebitoDinheiro(null, 178, "Fatura Telefone e Internet",
				LocalDate.parse("17/01/2023", formatter), false, null, FormaDePagamento.DEBITO, contas, group_admin,
				conta_familia);

		// Salvando despesas no banco de dados
		despesaDebitoDinheiroRepository.saveAll(Arrays.asList(almoco_setembro, jantar_setembro, lanche_tarde_setembro,
				supermercado_compras_setembro, almoco, jantar, lanche_tarde, supermercado_compras, almoco1_novembro,
				jantar1_novembro, lanche_tarde1_novembro, supermercado_compras1_novembro, almoco2_novembro, jantar2_novembro,
				lanche_tarde2_novembro, supermercado_compras_novembro, almoco1_dezembro, jantar1_dezembro, lanche_tarde1_dezembro,
				supermercado_compras1_dezembro, almoco2_dezembro, jantar2_dezembro, lanche_tarde2_dezembro,
				supermercado_compras_dezembro, almoco1_janeiro, jantar1_janeiro, lanche_tarde1_janeiro,
				supermercado_compras1_janeiro, almoco2_janeiro, jantar2_janeiro, lanche_tarde2_janeiro, luz_novembro, cond_novembro,
				internet_novembro, luz_dezembro, cond_dezembro, internet_dezembro, luz_janeiro, cond_janeiro, internet_janeiro));

		// -------------------------------------------------------------------------
		// Inserindo despesas no crédito
		DespesaCredito exameRotina = new DespesaCredito(null, 1400, "Exames de rotina das crianças",
				LocalDate.parse("18/09/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, saude,
				group_admin, 6, santander);

		DespesaCredito viagemPraia = new DespesaCredito(null, 1250, "Viagem para a praia",
				LocalDate.parse("15/10/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, lazer,
				group_admin, 24, bb);
		DespesaCredito roupas = new DespesaCredito(null, 1200, "Roupas para as crianças",
				LocalDate.parse("07/10/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, vestuario,
				group_admin, 15, santander);

		DespesaCredito exameRotina_novembro = new DespesaCredito(null, 1400, "Exames de rotina das crianças",
				LocalDate.parse("18/11/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, saude,
				group_admin, 6, santander);
		DespesaCredito viagemPraia_novembro = new DespesaCredito(null, 1250, "Viagem para a praia",
				LocalDate.parse("15/11/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, lazer,
				group_admin, 24, bb);
		DespesaCredito roupas_novembro = new DespesaCredito(null, 1200, "Roupas para as crianças",
				LocalDate.parse("07/11/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, vestuario,
				group_admin, 15, santander);
		DespesaCredito gasolina_novembro = new DespesaCredito(null, 254, "Gasolina - tanque cheio",
				LocalDate.parse("15/11/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, transporte,
				group_admin, 2, santander);

		DespesaCredito exameRotina_dezembro = new DespesaCredito(null, 1400, "Exames de rotina das crianças",
				LocalDate.parse("18/12/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, saude,
				group_admin, 6, santander);
		DespesaCredito viagemPraia_dezembro = new DespesaCredito(null, 1250, "Viagem para a praia",
				LocalDate.parse("15/12/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, lazer,
				group_admin, 24, bb);
		DespesaCredito roupas_dezembro = new DespesaCredito(null, 1200, "Roupas para as crianças",
				LocalDate.parse("07/12/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, vestuario,
				group_admin, 15, santander);
		DespesaCredito gasolina_dezembro = new DespesaCredito(null, 254, "Gasolina - tanque cheio",
				LocalDate.parse("15/12/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, transporte,
				group_admin, 2, santander);

		DespesaCredito exameRotina_janeiro = new DespesaCredito(null, 1400, "Exames de rotina das crianças",
				LocalDate.parse("18/01/2023", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, saude,
				group_admin, 6, santander);
		DespesaCredito viagemPraia_janeiro = new DespesaCredito(null, 1250, "Viagem para a praia",
				LocalDate.parse("15/01/2023", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, lazer,
				group_admin, 24, bb);
		DespesaCredito roupas_janeiro = new DespesaCredito(null, 1200, "Roupas para as crianças",
				LocalDate.parse("07/01/2023", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, vestuario,
				group_admin, 15, santander);
		DespesaCredito gasolina_janeiro = new DespesaCredito(null, 254, "Gasolina - tanque cheio",
				LocalDate.parse("15/01/2023", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, transporte,
				group_admin, 2, santander);

		// Salvando despesas no banco de dados
		despesaCreditoRepository.saveAll(Arrays.asList(exameRotina, viagemPraia, roupas, exameRotina_novembro,
				viagemPraia_novembro, roupas_novembro, exameRotina_dezembro, viagemPraia_dezembro, roupas_dezembro, exameRotina_janeiro,
				viagemPraia_janeiro, roupas_janeiro, gasolina_novembro, gasolina_dezembro, gasolina_janeiro));

		// -------------------------------------------------------------------------
		// Inserindo despesas de empréstimos e financiamentos
		DespesaFinanciamentoEmprestimo motoPai = new DespesaFinanciamentoEmprestimo(null, 8000, "Compra da moto do pai",
				LocalDate.parse("02/09/2022", formatter), false, null, FormaDePagamento.FINANCIAMENTO, transporte,
				group_user, 36, Banco.BANCO_DO_BRASIL);
		DespesaFinanciamentoEmprestimo quitarDividas = new DespesaFinanciamentoEmprestimo(null, 2000,
				"Empréstimo para quitar dívidas", LocalDate.parse("01/09/2022", formatter), false, null,
				FormaDePagamento.EMPRESTIMO, contas, group_user, 12, Banco.ITAU);
		DespesaFinanciamentoEmprestimo carro = new DespesaFinanciamentoEmprestimo(null, 40000,
				"Compra do carro da família", LocalDate.parse("01/10/2022", formatter), false, null,
				FormaDePagamento.FINANCIAMENTO, transporte, group_user, 48, Banco.BANCO_DO_BRASIL);

		// Salvando despesas no banco de dados
		despesaFinanciamentoEmprestimoRepository.saveAll(Arrays.asList(motoPai, quitarDividas, carro));
	}

}
