package br.com.mateus.projetoalura.ui.activity;

import static br.com.mateus.projetoalura.ui.activity.ConstantesParaAsActivities.CHAVE_DO_CLIENTE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.mateus.projetoalura.R;
import br.com.mateus.projetoalura.dao.ClienteDAO;
import br.com.mateus.projetoalura.model.Cliente;

public class FormularioActivity extends AppCompatActivity {

    private static final String FORMULARIO_DE_CADASTRO = "Formulário de cadastro";
    private static final String FORMULARIO_DE_EDICAO = "Formulário de edição";
    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoTelefone;
    private EditText campoCelular;
    private EditText campoCep;
    private EditText campoLogradouro;
    private EditText campoNumero;
    private EditText campoBairro;
    private EditText campoComplemento;
    private EditText campoCidade;
    private EditText campoEstado;
    private final ClienteDAO dao = new ClienteDAO();
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        setTitle(FORMULARIO_DE_CADASTRO);
        inicializacaoDosCampos();
        configuraBotaoSalvar();
        carregaCliente();
    }

    private void carregaCliente() {
        Intent dadosDoCliente = getIntent();
        if (dadosDoCliente.hasExtra(CHAVE_DO_CLIENTE)) {
            setTitle(FORMULARIO_DE_EDICAO);
            cliente = (Cliente) dadosDoCliente.getSerializableExtra(CHAVE_DO_CLIENTE);
            preencheCampo();
        } else {
            cliente = new Cliente();
        }
    }

    private void preencheCampo() {
        campoNome.setText(cliente.getNome());
        campoEmail.setText(cliente.getEmail());
        campoTelefone.setText(cliente.getTelefone());
        campoCelular.setText(cliente.getCelular());
        campoCep.setText(cliente.getCep());
        campoLogradouro.setText(cliente.getLogradouro());
        campoNumero.setText(cliente.getNumero());
        campoBairro.setText(cliente.getBairro());
        campoComplemento.setText(cliente.getComplemente());
        campoCidade.setText(cliente.getCidade());
        campoEstado.setText(cliente.getEstado());
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_input_nome);
        campoEmail = findViewById(R.id.activity_formulario_input_email);
        campoTelefone = findViewById(R.id.activity_formulario_input_telefone);
        campoCelular = findViewById(R.id.activity_formulario_input_celular);
        campoCep = findViewById(R.id.activity_formulario_input_cep);
        campoLogradouro = findViewById(R.id.activity_formulario_input_logradouro);
        campoNumero = findViewById(R.id.activity_formulario_input_numero);
        campoBairro = findViewById(R.id.activity_formulario_input_bairro);
        campoComplemento = findViewById(R.id.activity_formulario_input_complemento);
        campoCidade = findViewById(R.id.activity_formulario_input_cidade);
        campoEstado = findViewById(R.id.activity_formulario_input_estado);
    }

    private void preencheCliente() {
        String nome = campoNome.getText().toString().trim();
        String email = campoEmail.getText().toString().trim();
        String telefone = campoTelefone.getText().toString().trim();
        String celular = campoCelular.getText().toString().trim();
        String cep = campoCep.getText().toString().trim();
        String logradouro = campoLogradouro.getText().toString().trim();
        String numero = campoNumero.getText().toString().trim();
        String bairro = campoBairro.getText().toString().trim();
        String complemento = campoComplemento.getText().toString().trim();
        String cidade = campoCidade.getText().toString().trim();
        String estado = campoEstado.getText().toString().trim();

        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setCelular(celular);
        cliente.setCep(cep);
        cliente.setLogradouro(logradouro);
        cliente.setNumero(numero);
        cliente.setBairro(bairro);
        cliente.setComplemente(complemento);
        cliente.setCidade(cidade);
        cliente.setEstado(estado);
    }

    private void finalizaFormulario() {
        preencheCliente();
        if (cliente.idValido()) {
            dao.edita(cliente);
        } else {
            dao.salva(cliente);
        }
        finish();
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.activity_formulario_botao_salva);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalizaFormulario();
            }
        });
    }
}
