package br.com.mateus.projetoalura.ui.activity;

import static br.com.mateus.projetoalura.ui.activity.ConstantesParaAsActivities.CHAVE_DO_CLIENTE;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.mateus.projetoalura.R;
import br.com.mateus.projetoalura.dao.ClienteDAO;
import br.com.mateus.projetoalura.model.Cliente;

public class MainActivity extends AppCompatActivity {

    public static final String LISTA_DE_CONTATOS = "Lista de contatos";
    private final ClienteDAO dao = new ClienteDAO();
    private ArrayAdapter<Cliente> adapter;
    private Cliente clienteEscolhido;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(LISTA_DE_CONTATOS);
        setContentView(R.layout.activity_main);
        configuraListaDeContatos();
        configuraFabAdd();
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaListaDeContatos();
    }

    private void atualizaListaDeContatos() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int idDoMenu = item.getItemId();

        if (idDoMenu == R.id.activity_main_menu_editar) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            clienteEscolhido = adapter.getItem(menuInfo.position);
            editar(clienteEscolhido);
        }

        if (idDoMenu == R.id.activity_main_menu_remover) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Cliente clienteEscolhido = adapter.getItem(menuInfo.position);
            remover(clienteEscolhido);
        }
        return super.onContextItemSelected(item);
    }

    private void editar(Cliente clienteEscolhido) {
        Intent vaiParaFormulario = new Intent(MainActivity.this, FormularioActivity.class);
        vaiParaFormulario.putExtra(CHAVE_DO_CLIENTE, clienteEscolhido);
        startActivity(vaiParaFormulario);
    }

    private void remover(Cliente clienteEscolhido) {
        dao.remove(clienteEscolhido);
        adapter.remove(clienteEscolhido);
    }

    private void configuraFabAdd() {
        FloatingActionButton botaoAdd = findViewById(R.id.activity_main_fab_add);
        botaoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FormularioActivity.class));
            }
        });
    }

    private void configuraListaDeContatos() {
        ListView listaContatos = findViewById(R.id.activity_main_listview);
        final List<Cliente> clientes = dao.todos();

        this.adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clientes);
        listaContatos.setAdapter(adapter);

        configuraListenerDeCliqueNoItem(listaContatos, clientes);

        registerForContextMenu(listaContatos);
    }

    private void configuraListenerDeCliqueNoItem(ListView listaContatos, List<Cliente> clientes) {
        listaContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
//                Cliente clienteEscolhidoPosicao = clientes.get(posicao);
                clienteEscolhido = (Cliente) adapterView.getItemAtPosition(posicao);
                vaiParaInfo(clienteEscolhido);
            }
        });
    }

    private void vaiParaInfo(Cliente clienteEscolhido) {
        Intent vaiParaInfo = new Intent(MainActivity.this, InfoActivity.class);
        vaiParaInfo.putExtra(CHAVE_DO_CLIENTE, clienteEscolhido);
        startActivity(vaiParaInfo);
    }
}