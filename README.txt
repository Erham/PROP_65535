Marc Català, Adrià Fors i Sergio Rodríguez | ENTREGA 1 PROP - GRUP 14.
-----------------------------------------------------------------------

**IMPORTANT 1: Els documents que adjuntem per ser provats s'han de ficar SI O SI dins d'una subcarpeta anomenada "Documentos" dins la localització de l'execució.

**IMPORTANT 2: Si es vol crear un nou document .html, ha de seguir EXACTAMENT el mateix model (localització dels camps "títol", "autor", "contingut" i "tema" que els documents descarregats. En cas contrari, el gestor no sabrà identificar correctament els camps del document.

* Aquesta és la llista de classes amb el seu corresponent driver, i totes les funcions disponibles, indicant-se com es poden provar i alguns detalls a tenir en compte.

**CJT_DOCUMENTS:** (driver_cjtdoc.jar)
- private void recalcular_maxid()
- private int trobar_id_disponible()
- public Cjt_Documents():                           <<Es pot provar directament en qualsevol funció. (Afegir, Modificar...)>>
- public Cjt_Documents(Document d):                 <<Es pot provar directament en qualsevol funció. (Afegir, Modificar...)>>
- public Map<Integer, Document> get_cjt():          <<Cal posar la comanda 'Altres' per provar-la>>
- public int get_maxid():                           <<Cal posar la comanda 'Altres' per provar-la>>
- public void set_cjt(Map<Integer, Document> cjt):  <<Cal posar la comanda 'Altres' per provar-la>>
- public void set_maxid(int max_id):                <<Cal posar la comanda 'Altres' per provar-la>>
- public int tamany():                              <<Cal posar la comanda 'Altres' per provar-la>>
- public void afegir():                             <<Cal posar la comanda 'Afegir' per provar-la>>
- public void esborrar(int key):                    <<Cal posar la comanda 'Esborrar' per provar-la>>
- public void esborrarDoc(String tit, String aut):  <<Cal posar la comanda 'Esborrar' per provar-la>>
- public void modificar(int id):                    <<Cal posar la comanda 'Modificar' per provar-la>>
- public void modificarDoc(String tit, String aut): <<Cal posar la comanda 'Modificar' per provar-la>>
- public void cerca_Autor(String autor):            <<Cal posar la comanda 'Consultar' per provar-la>>
- public void cerca_Prefix(String prefix):          <<Cal posar la comanda 'Consultar' per provar-la>>
- public void cerca_Document(String titol, String autor): <<Cal posar la comanda 'Consultar' per provar-la>>
- public void cerca_Semblants(Document d, int k):   <<Cal posar la comanda 'Consultar' per provar-la>>
- public boolean existeix(Document d):             <<El seu funcionament ja es demostra quan s'esborra o s'afegeix un document.>>
- public boolean existeix_id(int id):              <<El seu funcionament ja es demostra quan s'esborra o s'afegeix un document.>>
- public int assignar_id_fitxer():                 <<El seu funcionament ja es demostra quan s'esborra o s'afegeix un document.>>
- public void omplir_cjt(String s):                <<Cal posar la comanda 'Altres' per provar-la.>>

**DOCUMENTS:** (driver_doc.jar)
- private String correction(String s):                     <<Quan es proven documents descarregats, el fet de que en imprimir els atributs no contingui símbols extranys és senyal de que la funció correcció és correcta.

- public Document():                                       <<Es prova a qualsevol funció. (Afegir, Modificar...)>>
- public Document(Frase titol, Frase autor, Paraula tema): <<Es prova a qualsevol funció. (Afegir, Modificar...)>>
- public Frase get_titol():                                <<Es prova introduint '1'>>
- public Frase get_autor():                                <<Es prova introduint '1'>>
- public Paraula get_tema():                               <<Es prova introduint '1'>>
- public Text get_contingut():                             <<Es prova introduint '1'>>
- public int get_id():                                     <<Es prova introduint '1'>>
- public void set_titol(Frase titol):                      
- public void set_autor(Frase autor):                     
- public void set_tema(Paraula tema):                      
- public void set_contingut(Text contingut):               <<Es prova introduint '0'>>
- public void set_titol_String(String s):                  <<Es prova introduint '0'>>
- public void set_autor_String(String s):                  <<Es prova introduint '0'>>
- public void set_contingut_string(String s):              <<Es prova introduint '0'>>
- public void set_tema_String(String s):                   <<Es prova introduint '0'>>
- public void imprimir():                                  <<Es prova introduint '2'>>
- public void set_all_data(String f):                      <<Es prova introduint '3'>>
- public void crear_divisions():                           <<Es prova introduint '4'>>

**TEXT:** (driver_text.jar)
- public Text()
- public Text(Frase p)
- public List<Frase> get_lf()
- public String get_textstring()
- public void set_lf(List<Frase> lf)
- public void set_textstring(String fin)
- public void afegir(Frase p)
- public void imprimir()
- public void imprimir_llista_frases()
- public void dividir()
- public void crear_divisions()
- public int count()

**FRASE:** (driver_frase.jar)
- public Frase()
- public Frase(Paraula p)
- public List<Paraula> get_lp()
- public String get_frasestring()
- public void set_lpar(List<Paraula> l_par)
- public void set_frasestring(String frasestring)
- public void imprimir()
- public void imprimir_llista_paraules()
- public int longitud()
- public void afegir(Paraula p)
- public void dividir()

**PARAULA:** (driver_paraula.jar)
- public Paraula()
- public Paraula(String P)
- public String get_p()
- public void set_p()
- public void imprimir()
- public boolean is_stop_word()

============================================================

**FITXER:**
 - public Fitxer() << Es prova en el menú 1.1
 - public static String path() << 1.1
 - protected BufferedWriter obrir_fitxer_w() << 1.3   
 - private BufferedReader obrir_fitxer_r() << 1.2
 - public String get_text() << 1.2
 - public void set_text(String t) << 1.2
 - public boolean creat() << 1.1
 - public boolean modificat() << 1.3
 - public void set_nom(String t) << 1.1
 - public void llegir() << 1.2
 - public void guardar() << 1.3
 - public void buidar_text() << 1.4
 - protected void crear(File a) << 1.1
 - public void eliminar() << 1.5

**ARXIU:** 
 - public Arxiu() << 2.1
 - public Arxiu(int id) << 2.3
 - public Arxiu(int id, String titol_1, String autor_1, String tema_1) << 2.2
 - private void ini() << 2.1
 - public String get_titol() << 2.1
 - public String get_autor() << 2.1
 - public String get_tema() << 2.1
 - public int get_id() << 2.1
 - public void set_titol(String t) << 2.1
 - public void set_autor(String a) << 2.1
 - public void set_tema(String t) << 2.1
 - public void set_id(int id) << 2.1
 
 **REGISTRE:** 
 - public Registre() << 3.1
 - private void ini() << 3.1
 - public void afegir_arxiu() << 3.2


**ESPAI_VECTORIAL:**
 - public Espai_vectorial() << 4.1
 - private void ini() << 4.1
 - public int get_dimensio() << 4.1
 - public void set_dimensio(int dim) << 4.1
 - private void omplir_map() << 4.2
 - public void esborrar_map() << 4.4
 - public void imprimir_map() << 4.2
 - public void afegir_al_espai(String doc) << 4.3
 - public void extreure_del_espai(String doc) << 4.3
 - private void modificar_map(String doc, boolean mode) << 4.3 (es veu pel funcionament de afegir i extreure)
 - private void omplir_list() << 4.2
 - public void imprimir_list() << 4.2
 - private Vector calcular_vector(String doc, boolean mode) << 4.5 (es veu pel funcionament de  vector_boolea i vector_enter)
 - public Vector vector_boolea(String doc) << 4.5
 - public Vector vector_enter(String doc) << 4.5
 - public void guardar() << 4.2

**COMPARADOR:**
 - public Comparador() << 5.1
 - public void assignar_vector_boolea(Espai_vectorial espai, String doc) << 5.1
 - public void assignar_vector_enter(Espai_vectorial espai, String doc) << 5.2 
 - public double distancia_boolea(Vector v2) << 5.3
 - public double cosinus_boolea(Vector v2) << 5.3
 - public double distancia_enter(Vector v2) << 5.3
 - private double distancia(Vector v2, int mode) << 5.3 (es veu pel funcionament de distancia_boolea, cosinus boolea...)

**VECTOR:**
 - public Vector() << 4.5
 - public void set_mida(int m) << 4.5
 - public int get_dimensio() << 4.5
 - public double[] get_elements() << 5.1
 - public void modificar_element(int index, double valor) << 4.5
 - public double get_element_i(int index) << 4.5
 - public void omplir_vector(String vec) << 5.3
 - public void normalitzar() << 5.3
 - public double modul() << 5.3
 - public void dividir() << 5.3
 - static Vector restar(Vector v1, Vector v2) << 5.3
 - static Vector clonar(Vector v) zz 5.3




