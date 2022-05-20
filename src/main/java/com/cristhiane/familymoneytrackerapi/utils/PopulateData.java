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
	//@PostConstruct
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

		Receita salarioMesMarcoPai = new Receita(null, 1900, "Salário do mês de Março - pai",
				LocalDate.parse("14/03/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita salarioMesMarcoMae = new Receita(null, 2000, "Salário do mês de Março - mãe",
				LocalDate.parse("09/03/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita salarioMesMarcoTio = new Receita(null, 2100, "Salário do mês de Março - tio",
				LocalDate.parse("07/03/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita mesadaMesMarco = new Receita(null, 245, "Mesada do mês de Março",
				LocalDate.parse("10/03/2022", formatter), false, null, mesada, rendaExtra, group_admin);
		Receita rendimentosMesMarco = new Receita(null, 65, "Rendimentos investimentos do mês de Março",
				LocalDate.parse("09/03/2022", formatter), false, null, poupanca_familia, rendimentoInvestimento,
				group_admin);

		Receita salarioMesAbrilPai = new Receita(null, 2000, "Salário do mês de Abril - pai",
				LocalDate.parse("15/04/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita salarioMesAbrilMae = new Receita(null, 2100, "Salário do mês de Abril - mãe",
				LocalDate.parse("10/04/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita salarioMesAbrilTio = new Receita(null, 2200, "Salário do mês de Abril - tio",
				LocalDate.parse("08/04/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita mesadaMesAbril = new Receita(null, 350, "Mesada do mês de Abril",
				LocalDate.parse("11/04/2022", formatter), false, null, mesada, rendaExtra, group_admin);
		Receita rendimentosMesAbril = new Receita(null, 70, "Rendimentos investimentos do mês de Abril",
				LocalDate.parse("08/04/2022", formatter), false, null, poupanca_familia, rendimentoInvestimento,
				group_admin);

		Receita salarioMesMaioPai = new Receita(null, 2541, "Salário do mês de Maio - pai",
				LocalDate.parse("15/05/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita salarioMesMaioMae = new Receita(null, 2654, "Salário do mês de Maio - mãe",
				LocalDate.parse("10/05/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita salarioMesMaioTio = new Receita(null, 2478, "Salário do mês de Maio - tio",
				LocalDate.parse("08/05/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita mesadaMesMaio = new Receita(null, 298, "Mesada do mês de Maio",
				LocalDate.parse("11/05/2022", formatter), false, null, mesada, rendaExtra, group_admin);
		Receita rendimentosMesMaio = new Receita(null, 71, "Rendimentos investimentos do mês de Maio",
				LocalDate.parse("08/05/2022", formatter), false, null, poupanca_familia, rendimentoInvestimento,
				group_admin);

		Receita salarioMesJunhoPai = new Receita(null, 2547, "Salário do mês de Junho - pai",
				LocalDate.parse("15/06/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita salarioMesJunhoMae = new Receita(null, 2698, "Salário do mês de Junho - mãe",
				LocalDate.parse("10/06/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita salarioMesJunhoTio = new Receita(null, 2874, "Salário do mês de Junho - tio",
				LocalDate.parse("08/06/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita mesadaMesJunho = new Receita(null, 244, "Mesada do mês de Junho",
				LocalDate.parse("11/06/2022", formatter), false, null, mesada, rendaExtra, group_admin);
		Receita rendimentosMesJunho = new Receita(null, 54, "Rendimentos investimentos do mês de Junho",
				LocalDate.parse("08/06/2022", formatter), false, null, poupanca_familia, rendimentoInvestimento,
				group_admin);

		Receita salarioMesJulhoPai = new Receita(null, 2687, "Salário do mês de Julho - pai",
				LocalDate.parse("15/07/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita salarioMesJulhoMae = new Receita(null, 2458, "Salário do mês de Julho - mãe",
				LocalDate.parse("10/07/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita salarioMesJulhoTio = new Receita(null, 2965, "Salário do mês de Julho - tio",
				LocalDate.parse("08/07/2022", formatter), false, null, conta_familia, salario, group_admin);
		Receita mesadaMesJulho = new Receita(null, 298, "Mesada do mês de Julho",
				LocalDate.parse("11/07/2022", formatter), false, null, mesada, rendaExtra, group_admin);
		Receita rendimentosMesJulho = new Receita(null, 68, "Rendimentos investimentos do mês de Julho",
				LocalDate.parse("08/07/2022", formatter), false, null, poupanca_familia, rendimentoInvestimento,
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
		DespesaDebitoDinheiro almoco_marco = new DespesaDebitoDinheiro(null, 57, "Almoço do dia 09/03",
				LocalDate.parse("09/03/2022", formatter), false, null, FormaDePagamento.DEBITO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro jantar_marco = new DespesaDebitoDinheiro(null, 46, "Jantar do dia 10/03",
				LocalDate.parse("10/03/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, dinheiro);
		DespesaDebitoDinheiro lanche_tarde_marco = new DespesaDebitoDinheiro(null, 18, "Lanche da tarde do dia 02/03",
				LocalDate.parse("02/03/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, mesada);
		DespesaDebitoDinheiro supermercado_compras_marco = new DespesaDebitoDinheiro(null, 400,
				"Compras no supermercado - 12/03", LocalDate.parse("12/03/2022", formatter), false, null,
				FormaDePagamento.DEBITO, alimentacao, group_admin, conta_familia);

		DespesaDebitoDinheiro almoco = new DespesaDebitoDinheiro(null, 50, "Almoço do dia 08/04",
				LocalDate.parse("08/04/2022", formatter), false, null, FormaDePagamento.DEBITO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro jantar = new DespesaDebitoDinheiro(null, 45, "Jantar do dia 07/04",
				LocalDate.parse("07/04/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro lanche_tarde = new DespesaDebitoDinheiro(null, 15, "Lanche da tarde do dia 06/04",
				LocalDate.parse("06/04/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, mesada);
		DespesaDebitoDinheiro supermercado_compras = new DespesaDebitoDinheiro(null, 250,
				"Compras no supermercado - 05/04", LocalDate.parse("05/04/2022", formatter), false, null,
				FormaDePagamento.DEBITO, alimentacao, group_admin, conta_familia);

		DespesaDebitoDinheiro almoco1_maio = new DespesaDebitoDinheiro(null, 47, "Almoço do dia 16/05",
				LocalDate.parse("16/05/2022", formatter), false, null, FormaDePagamento.DEBITO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro jantar1_maio = new DespesaDebitoDinheiro(null, 36, "Jantar do dia 03/05",
				LocalDate.parse("03/05/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, dinheiro);
		DespesaDebitoDinheiro lanche_tarde1_maio = new DespesaDebitoDinheiro(null, 13, "Lanche da tarde do dia 04/05",
				LocalDate.parse("04/05/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, mesada);
		DespesaDebitoDinheiro supermercado_compras1_maio = new DespesaDebitoDinheiro(null, 369,
				"Compras no supermercado - 02/05", LocalDate.parse("02/05/2022", formatter), false, null,
				FormaDePagamento.DEBITO, alimentacao, group_admin, conta_familia);
		DespesaDebitoDinheiro almoco2_maio = new DespesaDebitoDinheiro(null, 41, "Almoço do dia 11/05",
				LocalDate.parse("11/05/2022", formatter), false, null, FormaDePagamento.DEBITO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro jantar2_maio = new DespesaDebitoDinheiro(null, 32, "Jantar do dia 20/05",
				LocalDate.parse("20/05/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro lanche_tarde2_maio = new DespesaDebitoDinheiro(null, 14, "Lanche da tarde do dia 08/05",
				LocalDate.parse("08/05/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, mesada);
		DespesaDebitoDinheiro supermercado_compras_maio = new DespesaDebitoDinheiro(null, 250,
				"Compras no supermercado - 03/05", LocalDate.parse("03/05/2022", formatter), false, null,
				FormaDePagamento.DEBITO, alimentacao, group_admin, conta_familia);
		DespesaDebitoDinheiro luz_maio = new DespesaDebitoDinheiro(null, 178, "Conta de Luz",
				LocalDate.parse("09/05/2022", formatter), false, null, FormaDePagamento.DEBITO, contas, group_admin,
				conta_familia);
		DespesaDebitoDinheiro cond_maio = new DespesaDebitoDinheiro(null, 567, "Boleto Condomínio",
				LocalDate.parse("22/05/2022", formatter), false, null, FormaDePagamento.DEBITO, contas, group_admin,
				conta_familia);
		DespesaDebitoDinheiro internet_maio = new DespesaDebitoDinheiro(null, 178, "Fatura Telefone e Internet",
				LocalDate.parse("17/05/2022", formatter), false, null, FormaDePagamento.DEBITO, contas, group_admin,
				conta_familia);

		DespesaDebitoDinheiro almoco1_junho = new DespesaDebitoDinheiro(null, 47, "Almoço do dia 16/06",
				LocalDate.parse("16/06/2022", formatter), false, null, FormaDePagamento.DEBITO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro jantar1_junho = new DespesaDebitoDinheiro(null, 36, "Jantar do dia 03/06",
				LocalDate.parse("03/06/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, dinheiro);
		DespesaDebitoDinheiro lanche_tarde1_junho = new DespesaDebitoDinheiro(null, 13, "Lanche da tarde do dia 04/06",
				LocalDate.parse("04/06/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, mesada);
		DespesaDebitoDinheiro supermercado_compras1_junho = new DespesaDebitoDinheiro(null, 369,
				"Compras no supermercado - 02/06", LocalDate.parse("02/06/2022", formatter), false, null,
				FormaDePagamento.DEBITO, alimentacao, group_admin, conta_familia);
		DespesaDebitoDinheiro almoco2_junho = new DespesaDebitoDinheiro(null, 41, "Almoço do dia 11/06",
				LocalDate.parse("11/06/2022", formatter), false, null, FormaDePagamento.DEBITO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro jantar2_junho = new DespesaDebitoDinheiro(null, 32, "Jantar do dia 20/06",
				LocalDate.parse("20/06/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro lanche_tarde2_junho = new DespesaDebitoDinheiro(null, 14, "Lanche da tarde do dia 08/06",
				LocalDate.parse("08/06/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, mesada);
		DespesaDebitoDinheiro supermercado_compras_junho = new DespesaDebitoDinheiro(null, 250,
				"Compras no supermercado - 03/06", LocalDate.parse("03/06/2022", formatter), false, null,
				FormaDePagamento.DEBITO, alimentacao, group_admin, conta_familia);
		DespesaDebitoDinheiro luz_junho = new DespesaDebitoDinheiro(null, 178, "Conta de Luz",
				LocalDate.parse("09/06/2022", formatter), false, null, FormaDePagamento.DEBITO, contas, group_admin,
				conta_familia);
		DespesaDebitoDinheiro cond_junho = new DespesaDebitoDinheiro(null, 567, "Boleto Condomínio",
				LocalDate.parse("22/06/2022", formatter), false, null, FormaDePagamento.DEBITO, contas, group_admin,
				conta_familia);
		DespesaDebitoDinheiro internet_junho = new DespesaDebitoDinheiro(null, 178, "Fatura Telefone e Internet",
				LocalDate.parse("17/06/2022", formatter), false, null, FormaDePagamento.DEBITO, contas, group_admin,
				conta_familia);

		DespesaDebitoDinheiro almoco1_julho = new DespesaDebitoDinheiro(null, 47, "Almoço do dia 16/07",
				LocalDate.parse("16/07/2022", formatter), false, null, FormaDePagamento.DEBITO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro jantar1_julho = new DespesaDebitoDinheiro(null, 36, "Jantar do dia 03/07",
				LocalDate.parse("03/07/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, dinheiro);
		DespesaDebitoDinheiro lanche_tarde1_julho = new DespesaDebitoDinheiro(null, 13, "Lanche da tarde do dia 04/07",
				LocalDate.parse("04/07/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, mesada);
		DespesaDebitoDinheiro supermercado_compras1_julho = new DespesaDebitoDinheiro(null, 369,
				"Compras no supermercado - 02/07", LocalDate.parse("02/07/2022", formatter), false, null,
				FormaDePagamento.DEBITO, alimentacao, group_admin, conta_familia);
		DespesaDebitoDinheiro almoco2_julho = new DespesaDebitoDinheiro(null, 41, "Almoço do dia 11/07",
				LocalDate.parse("11/07/2022", formatter), false, null, FormaDePagamento.DEBITO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro jantar2_julho = new DespesaDebitoDinheiro(null, 32, "Jantar do dia 20/07",
				LocalDate.parse("20/07/2022", formatter), false, null, FormaDePagamento.DINHEIRO, alimentacao,
				group_admin, conta_familia);
		DespesaDebitoDinheiro lanche_tarde2_julho = new DespesaDebitoDinheiro(null, 250,
				"Compras no supermercado - 03/07", LocalDate.parse("03/07/2022", formatter), false, null,
				FormaDePagamento.DEBITO, alimentacao, group_admin, conta_familia);
		DespesaDebitoDinheiro luz_julho = new DespesaDebitoDinheiro(null, 178, "Conta de Luz",
				LocalDate.parse("09/07/2022", formatter), false, null, FormaDePagamento.DEBITO, contas, group_admin,
				conta_familia);
		DespesaDebitoDinheiro cond_julho = new DespesaDebitoDinheiro(null, 567, "Boleto Condomínio",
				LocalDate.parse("22/07/2022", formatter), false, null, FormaDePagamento.DEBITO, contas, group_admin,
				conta_familia);
		DespesaDebitoDinheiro internet_julho = new DespesaDebitoDinheiro(null, 178, "Fatura Telefone e Internet",
				LocalDate.parse("17/07/2022", formatter), false, null, FormaDePagamento.DEBITO, contas, group_admin,
				conta_familia);

		// Salvando despesas no banco de dados
		despesaDebitoDinheiroRepository.saveAll(Arrays.asList(almoco_marco, jantar_marco, lanche_tarde_marco,
				supermercado_compras_marco, almoco, jantar, lanche_tarde, supermercado_compras, almoco1_maio,
				jantar1_maio, lanche_tarde1_maio, supermercado_compras1_maio, almoco2_maio, jantar2_maio,
				lanche_tarde2_maio, supermercado_compras_maio, almoco1_junho, jantar1_junho, lanche_tarde1_junho,
				supermercado_compras1_junho, almoco2_junho, jantar2_junho, lanche_tarde2_junho,
				supermercado_compras_junho, almoco1_julho, jantar1_julho, lanche_tarde1_julho,
				supermercado_compras1_julho, almoco2_julho, jantar2_julho, lanche_tarde2_julho, luz_maio, cond_maio,
				internet_maio, luz_junho, cond_junho, internet_junho, luz_julho, cond_julho, internet_julho));

		// -------------------------------------------------------------------------
		// Inserindo despesas no crédito
		DespesaCredito exameRotina = new DespesaCredito(null, 1400, "Exames de rotina das crianças",
				LocalDate.parse("18/03/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, saude,
				group_admin, 6, santander);

		DespesaCredito viagemPraia = new DespesaCredito(null, 1250, "Viagem para a praia",
				LocalDate.parse("15/04/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, lazer,
				group_admin, 24, bb);
		DespesaCredito roupas = new DespesaCredito(null, 1200, "Roupas para as crianças",
				LocalDate.parse("07/04/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, vestuario,
				group_admin, 15, santander);

		DespesaCredito exameRotina_maio = new DespesaCredito(null, 1400, "Exames de rotina das crianças",
				LocalDate.parse("18/05/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, saude,
				group_admin, 6, santander);
		DespesaCredito viagemPraia_maio = new DespesaCredito(null, 1250, "Viagem para a praia",
				LocalDate.parse("15/05/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, lazer,
				group_admin, 24, bb);
		DespesaCredito roupas_maio = new DespesaCredito(null, 1200, "Roupas para as crianças",
				LocalDate.parse("07/05/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, vestuario,
				group_admin, 15, santander);
		DespesaCredito gasolina_maio = new DespesaCredito(null, 254, "Gasolina - tanque cheio",
				LocalDate.parse("15/05/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, transporte,
				group_admin, 2, santander);

		DespesaCredito exameRotina_junho = new DespesaCredito(null, 1400, "Exames de rotina das crianças",
				LocalDate.parse("18/06/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, saude,
				group_admin, 6, santander);
		DespesaCredito viagemPraia_junho = new DespesaCredito(null, 1250, "Viagem para a praia",
				LocalDate.parse("15/06/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, lazer,
				group_admin, 24, bb);
		DespesaCredito roupas_junho = new DespesaCredito(null, 1200, "Roupas para as crianças",
				LocalDate.parse("07/06/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, vestuario,
				group_admin, 15, santander);
		DespesaCredito gasolina_junho = new DespesaCredito(null, 254, "Gasolina - tanque cheio",
				LocalDate.parse("15/06/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, transporte,
				group_admin, 2, santander);

		DespesaCredito exameRotina_julho = new DespesaCredito(null, 1400, "Exames de rotina das crianças",
				LocalDate.parse("18/07/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, saude,
				group_admin, 6, santander);
		DespesaCredito viagemPraia_julho = new DespesaCredito(null, 1250, "Viagem para a praia",
				LocalDate.parse("15/07/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, lazer,
				group_admin, 24, bb);
		DespesaCredito roupas_julho = new DespesaCredito(null, 1200, "Roupas para as crianças",
				LocalDate.parse("07/07/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, vestuario,
				group_admin, 15, santander);
		DespesaCredito gasolina_julho = new DespesaCredito(null, 254, "Gasolina - tanque cheio",
				LocalDate.parse("15/07/2022", formatter), false, null, FormaDePagamento.CARTAO_DE_CREDITO, transporte,
				group_admin, 2, santander);

		// Salvando despesas no banco de dados
		despesaCreditoRepository.saveAll(Arrays.asList(exameRotina, viagemPraia, roupas, exameRotina_maio,
				viagemPraia_maio, roupas_maio, exameRotina_junho, viagemPraia_junho, roupas_junho, exameRotina_julho,
				viagemPraia_julho, roupas_julho, gasolina_maio, gasolina_junho, gasolina_julho));

		// -------------------------------------------------------------------------
		// Inserindo despesas de empréstimos e financiamentos
		DespesaFinanciamentoEmprestimo motoPai = new DespesaFinanciamentoEmprestimo(null, 8000, "Compra da moto do pai",
				LocalDate.parse("02/03/2022", formatter), false, null, FormaDePagamento.FINANCIAMENTO, transporte,
				group_user, 36, Banco.BANCO_DO_BRASIL);
		DespesaFinanciamentoEmprestimo quitarDividas = new DespesaFinanciamentoEmprestimo(null, 2000,
				"Empréstimo para quitar dívidas", LocalDate.parse("01/03/2022", formatter), false, null,
				FormaDePagamento.EMPRESTIMO, contas, group_user, 12, Banco.ITAU);
		DespesaFinanciamentoEmprestimo carro = new DespesaFinanciamentoEmprestimo(null, 40000,
				"Compra do carro da família", LocalDate.parse("01/04/2022", formatter), false, null,
				FormaDePagamento.FINANCIAMENTO, transporte, group_user, 48, Banco.BANCO_DO_BRASIL);

		// Salvando despesas no banco de dados
		despesaFinanciamentoEmprestimoRepository.saveAll(Arrays.asList(motoPai, quitarDividas, carro));
	}

}
