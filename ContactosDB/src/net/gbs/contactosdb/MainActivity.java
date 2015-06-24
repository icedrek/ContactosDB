package net.gbs.contactosdb;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Se define listener para el boton INSERTAR
		Button btInsertar = (Button)findViewById(R.id.Bt_Insertar);
		btInsertar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Se recuperan los campos de la pantalla
				EditText etNombre = (EditText)findViewById(R.id.ET_Nombre);
				EditText etEmail = (EditText)findViewById(R.id.ET_Email);
				EditText etTelefono = (EditText)findViewById(R.id.ET_Telefono);
				
				String nombre = etNombre.getText().toString();
				String email = etEmail.getText().toString();
				Integer telefono = Integer.parseInt(etTelefono.getText().toString());
				
				//Se insertaregistro en Base de Datos
				insertar(nombre,email,telefono);

				//Se borran datos de pantalla
				etNombre.setText(null);
				etEmail.setText(null);
				etTelefono.setText(null);
			}
		});		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void insertar(String nombre, String email, int telefono){
		//Abrimos la base de datos 'DBUsuarios' en modo escritura
        ContactosSQL usdbh =
            new ContactosSQL(this, "DBContactos", null, 1);
 
        SQLiteDatabase db = usdbh.getWritableDatabase();
 
        //Si hemos abierto correctamente la base de datos
        if(db != null) {
        	try {
        		//Insertamos los datos en la tabla Contactos
        		db.execSQL("INSERT INTO Contactos (nombre, email, telefono) " +
        					"VALUES (" + "'" + nombre +"', "+ "'" + email +"', "+ telefono +")");
 
        		//Cerramos la base de datos
        		db.close();
            	Toast.makeText(this,"Los datos fueron grabados", Toast.LENGTH_LONG).show();
        	}catch(Exception e){
        		Toast.makeText(this,"ERROR al escribir datos", Toast.LENGTH_LONG).show();
        	}
        }
	}
	
	public void consultar(View v) {
		Intent listado = new Intent(this, ContactosActivity.class);
		startActivity(listado);				
	}
}
