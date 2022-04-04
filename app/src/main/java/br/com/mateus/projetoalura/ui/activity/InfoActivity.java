package br.com.mateus.projetoalura.ui.activity;

import static br.com.mateus.projetoalura.ui.activity.ConstantesParaAsActivities.CHAVE_DO_CLIENTE;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

import br.com.mateus.projetoalura.R;
import br.com.mateus.projetoalura.model.Cliente;

public class InfoActivity extends AppCompatActivity {

    public static final String INFORMAÇÕES_DOS_CLIENTES = "Informações do cliente";
    private TextView campoNome;
    private TextView campoEmail;
    private TextView campoTelefone;
    private TextView campoCelular;
    private TextView campoCep;
    private TextView campoLogradouro;
    private TextView campoNumero;
    private TextView campoBairro;
    private TextView campoComplemento;
    private TextView campoCidade;
    private TextView campoEstado;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        setTitle(INFORMAÇÕES_DOS_CLIENTES);
        inicializaCampos();
        carregaCliente();

    }

    private void carregaCliente() {
        Intent dadosDoCliente = getIntent();
        if (dadosDoCliente.hasExtra(CHAVE_DO_CLIENTE)) {
            cliente = (Cliente) dadosDoCliente.getSerializableExtra(CHAVE_DO_CLIENTE);
            preencheCampos();
        }
    }

    private void preencheCampos() {
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

    private void inicializaCampos() {
        campoNome = findViewById(R.id.activity_info_text_nome);
        campoEmail = findViewById(R.id.activity_info_text_email);
        campoTelefone = findViewById(R.id.activity_info_text_telefone);
        campoCelular = findViewById(R.id.activity_info_text_celular);
        campoCep = findViewById(R.id.activity_info_text_cep);
        campoLogradouro = findViewById(R.id.activity_info_text_logradouro);
        campoNumero = findViewById(R.id.activity_info_text_numero);
        campoBairro = findViewById(R.id.activity_info_text_bairro);
        campoComplemento = findViewById(R.id.activity_info_text_complemento);
        campoCidade = findViewById(R.id.activity_info_text_cidade);
        campoEstado = findViewById(R.id.activity_info_text_estado);
    }
}
