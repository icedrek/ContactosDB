package net.gbs.contactosdb;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ContactosActivity extends Activity {
	Contacto[] datos=new Contacto[100];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contactos);
		
		int i = 0;
		try{ 
		//Abrimos la base de datos 'DBUsuarios' en modo escritura
        ContactosSQL contacdbh =
            new ContactosSQL(this, "DBContactos", null, 1);
 
        SQLiteDatabase db = contacdbh.getWritableDatabase();
		
		//String[] campos = new String[] {"nombre", "email", "telefono"};
		//String[] args = new String[] {"contact1"};
		
		Cursor c = db.rawQuery("SELECT * FROM Contactos", null);
		// db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
		//Nos aseguramos de que existe al menos un registro
		if (c.moveToFirst()) {
		     //Recorremos el cursor hasta que no haya más registros
		     do {
		          String nombre= c.getString(0);
		          String email = c.getString(1);
		          String telefono = c.getString(2);
		          
		          Contacto contacto = new Contacto(nombre, email, Integer.parseInt(telefono));
		          
		          datos[i] = contacto;
		          i++;
		          
		     } while(c.moveToNext());
		}
		
		AdaptadorContactos adaptador =
				new AdaptadorContactos(this);
         
        ListView lstContactos = (ListView)findViewById(R.id.LstContactos);        
        lstContactos.setAdapter(adaptador);
		}catch(NullPointerException npe){
			Toast.makeText(this,"No se han encontrado datos", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contactos, menu);
		return true;
	}
	
	public class AdaptadorContactos extends ArrayAdapter<Object> {
    	Activity context;
    	    	 
        AdaptadorContactos(Activity context) {
            super(context, R.layout.list_contacto, datos);
            this.context = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
        	LayoutInflater inflater = context.getLayoutInflater();
        	View item = inflater.inflate(R.layout.list_contacto, null);

        	TextView tvNombre = (TextView)item.findViewById(R.id.tvNombre);
        	tvNombre.setText(datos[position].getNombre());

        	TextView tvEmail = (TextView)item.findViewById(R.id.tvEmail);
        	tvEmail.setText(datos[position].getEmail());
        
        	TextView tvTelefono = (TextView)item.findViewById(R.id.tvTelefono);
        	tvTelefono.setText(datos[position].getTelefono().toString());

        	return(item);
        }
    }

}
