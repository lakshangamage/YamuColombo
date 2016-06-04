package com.phrixus.yamucolombo.yamucolomboversion10.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.phrixus.yamucolombo.yamucolomboversion10.Controller.RecyclerAdaptor;
import com.phrixus.yamucolombo.yamucolomboversion10.R;

public class MainActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String[] route_no={"100","101","102","103","104","107","112","119","120","122","125","138","140","141","143","144","145","148","150","151","152","153","154"};
    private String[] from_to={"Pettah - Panadura", "Pettah - Moratuwa", "Kotahena - Angulana", "Fort - Narahenpita","Ja-Ela - Bambalapitiya","Elakanda - Fort","Kotahena - Maharagama", "Dehiwala - Maharagama", "Pettah - Horana", "Pettah - Avissawella","Pettah - Ingiriya","Pettah - Homagama","Kollupitiya - Wellampitiya","Narahenpita - Wellawatte", "Fort - Hanwella"," Fort - Rajagiriya","Mattakkuliya - Seemamalakaya","Fort - Labugama", "Gangarama - Kelanimulla","Fort - Ambatale","Fort - Koswatte","Borella - Nawarohala","Kiribathgoda - Angulana"};
    private String[] routeinfo = {" Pettah -> Fort -> Galle Face -> Kollupitiya -> Bambalapitiya -> Wellawatta -> Dehiwala -> Mt.Lavinia -> Ratmalana -> Soysapura -> Angulana -> Katubadda -> Rawatawatta -> Moratuwa -> Keselwatta -> Walana -Panadura ",
            " Pettah -> Fort -> Lake house -> Slave Island -> Gangarama -> Kollupitiya -> Bambalapitiya -> Wellawatta -> Dehiwala -> Mt.Lavinia -> Ratmalana-> Soysapura -> Angulana -> Katubadda -> Rawatawatta -> Moratuwa ",
            " Kotahena -> Kochikade -> Khan Clock Tower -> Galle Face -> Kollupitiya -> Bambalapitiya -> Wellawatta -> Dehiwala -> Mt.Lavinia -> Ratmalana -> Soysapura -> Angulana ",
            " Fort -> Pettah -> Hultsdorf -> Maradana Technical College Junction -> Panchikawatte -> Maradana -> Punchi Borella -> Borella -> Kanatta Junction -> Narahenpita ",
            " Ja-ela -> Wattala -> New Kelani Bridge -> Orugodawatta -> Dematagoda -> Borella -> Kanatta Junction -> Jawatte -> Colombo Campus -> Thummulla -> Bambalapitiya ",
            " Elakanda -> Handala Junction -> Hekitta Junction -> Wattala -> Peliyagoda -> Old Kelani Bridge -> Bloemendhal -> Armour Street -> Kotahena -> Kochikade -> Khan Clock Tower -> Fort ",
            " Kotahena -> Kochikade -> Khan Clock Tower -> Lake house -> Slave Island -> Gangarama -> Kollupitiya -> Bambalapitiya -> Thimbirigasyaya -> Havlock City -> Kirulapone -> Nugegoda -> Delkanda -> Navinna -> Maharagama ",
            " Dehiwala -> Karagampitiya -> Nedimala -> Bellantota Junction -> Boralesgamuwa -> Maharagama ",
            " Pettah -> Fort -> Lake house -> Gamini Hall Junction -> Town Hall -> Independence Ave -> Colombo Campus -> Thummulla -> Thimbirigasyaya -> Havlock City -> Kirulapone -> Kohuwala -> Pepiliyana -> Rattanapitiya -> Boralesgamuwa -> Piliyandala -> Kesbawa -> Kahatuduwa -> Gonapala -> Pokunuwita -> Horana ",
            " Pettah -> Fort -> Lake house -> Gamini Hall Junction -> Town Hall -> Independence Ave -> Colombo Campus -> Thummulla -> Thimbirigasyaya -> Havlock City -> Kirulapone -> Nugegoda -> Delkanda -> Navinna -> Maharagama -> Pannipitiya -> Kottawa -> Makumbura -> Homagama -> Godagama -> Meegoda -> Meepe ->  Pahatgama -> Kaluaggala -> Kosgama -> Avissawella ",
            " Pettah -> Fort -> Lake house -> Gamini Hall Junction -> Town Hall -> Independence Ave -> Colombo Campus -> Thummulla -> Thimbirigasyaya -> Havlock City -> Kirulapone -> Nugegoda -> Delkanda -> Navinna -> Maharagama -> Pannipitiya -> Kottawa -> Makumbura -> Homagama -> Godagama -> Meegoda -> Padukka -> Handapangoda -> Ingiriya ",
            " Pettah -> Fort -> Lake house -> Salve Island -> Town Hall -> Colombo Public Library -> Independence Ave -> Colombo Campus -> Thummulla -> Thimbirigasyaya -> Havlock City -> Kirulapone -> Nugegoda -> Delkanda -> Navinna -> Maharagama -> Pannipitiya -> Kottawa -> Homagama ",
            " Kollupitiya -> Town Hall -> Norris Canal Road -> Paranawadiya -> Maradana -> Dematagoda -> Kolonnawa -> Wellampitiya ",
            " Narahenpita -> Baseline Road -> Kirulapone -> Pamankada ->W.A Silva Road -> Wellawatte ",
            " Fort -> Pettah -> Maradana Technical College Junction -> Panchikawatte -> Armour Street -> Kosgas Junction -> Orugodawatta -> Wellampitiya -> Kotikawaththa -> Angoda Junction -> Ambatale -> Welivita -> Kaduwela -> Bomiriya -> Nawagamuwa -> Ranala -> Artigala -> Hanwella ",
            " Fort -> Pettah -> Maradana Technical College Junction -> Panchikawatte -> Maradana -> Punchi Borella -> Borella -> Rajagiriya ",
            " Mattakuliya -> Modara -> Hettiyawatta -> Kochikade -> Khan Clock Tower -> Lake House -> Slave Island -> Gangarama -> Seemamalakaya ",
            " Fort -> Pettah -> Maradana Technical College Junction -> Panchikawatte -> Kosgas Junction -> Orugodawatta -> Wellampitiya -> Kotikawaththa -> Angoda Junction -> Ambatale -> Welivita -> Kaduwela -> Bomiriya -> Nawagamuwa -> Ranala -> Artigala -> Hanwella -> Kaluaggala -> Kahahena -> Welikanna -> Waga -> Thummodara -> Labugama ",
            " Gangarama -> Seemamalakaya -> Town Hall -> Borella -> Rajagiriya -> Ambagaha Junction -> Kalapaluwawa -> Aggona Junction -> Walpola Junction -> Angoda Junction -> Kelanimulla ",
            " Fort -> Pettah -> Maradana Technical College Junction -> Panchikawatte -> Armour Street -> Kosgas Junction -> Thotalanga -> New Kelani Bridge -> Halmulla -> Maha Buthgamuwa -> Kohilawatta -> Ambatale ",
            " Fort -> Pettah -> Maradana -> Technical college junction -> Panchikawatte -> Armour Street -> Kosgas Junction -> Orugodawatta -> Dematagoda -> Kolonnawa -> Gothatuwa -> Ambagaha Junction -> Kalapaluwawa -> Aggona Junction -> Koswatta ",
            " Borella -> Rajagiriya -> Ethulkotte -> Pitakotte -> Baddagana -> Madiwela Junction -> Nawarohala ",
            " Kiribathgoda -> Kelaniya -> Peliyagoda -> New Kelani Bridge -> Orugodawatta -> Dematagoda -> Borella -> Kanatta -> Jawatte -> Colombo Campus -> Thummulla -> Bambalapitiya -> Wellawatte -> Dehiwala -> Mt.Lavinia -> Ratmalana -> Soysapura -> Agulana "};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new RecyclerAdaptor(route_no,from_to,routeinfo,this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}

