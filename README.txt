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

**FITXER:**
- public Fitxer()
- public static String path()
- protected BufferedWriter obrir_fitxer_w()
- private BufferedReader obrir_fitxer_r()
- public String get_text()
- public void set_text(String t)
- public boolean creat()
- public boolean modificat()
- public void set_nom(String t)
- public void llegir()
- public void guardar()
- public void buidar_text()
- protected void crear(File a)
- public void eliminar()

**ARXIU:** 
- public Arxiu()
- public Arxiu(int id)
- public Arxiu(int id, String titol_1, String autor_1, String tema_1)
- private void ini()
- public String get_titol()
- public String get_autor()
- public String get_tema()
- public int get_id()
- public void set_titol(String t)
- public void set_autor(String a)
- public void set_tema(String t)
- public void set_id(int id)


**ESPAI_VECTORIAL:**
- public Espai_vectorial()
- private void ini()
- public int get_dimensio()
- public void set_dimensio(int dim)
- private void omplir_map()
- public void esborrar_map()
- public void imprimir_map()
- public void afegir_al_espai(String doc)
- public void extreure_del_espai(String doc)
- private void modificar_map(String doc, boolean mode)
- private void omplir_list()
- public void imprimir_list()
- private Vector calcular_vector(String doc, boolean mode)
- public Vector vector_boolea(String doc)
- public Vector vector_enter(String doc)
- public void guardar()

**COMPARADOR:**
- public Comparador()
- public void assignar_vector_boolea(Espai_vectorial espai, String doc)
- public void assignar_vector_enter(Espai_vectorial espai, String doc)
- public double distancia_boolea(Vector v2)
- public double cosinus_boolea(Vector v2)
- public double distancia_enter(Vector v2)
- private double distancia(Vector v2, int mode)

**VECTOR:**
- public Vector()
- public void set_mida(int m)
- public int get_dimensio()
- public double[] get_elements()
- public void modificar_element(int index, double valor)
- public double get_element_i(int index)
- public void omplir_vector(String vec)
- public void normalitzar()
- public double modul()
- public void dividir()
- static Vector restar(Vector v1, Vector v2)
- static Vector clonar(Vector v)

**REGISTRE:** 
- public Registre()
- private void ini()
- public void afegir_arxiu()


