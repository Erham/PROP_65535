Marc Català, Adrià Fors i Sergio Rodríguez | ENTREGA 1 PROP - GRUP 14.
-----------------------------------------------------------------------

**IMPORTANT 1: Els documents que adjuntem per ser provats s'han de ficar SI O SI dins d'una subcarpeta anomenada "Documentos" dins la localització de l'execució.

**IMPORTANT 2: Si es vol crear un nou document .html, ha de seguir EXACTAMENT el mateix model (localització dels camps "títol", "autor", "contingut" i "tema" que els documents descarregats. En cas contrari, el gestor o sabrà identificar correctament els camps del document.

* Aquesta és la llista de classes amb el seu corresponent driver, i totes les funcions disponibles, indicant-se com es poden provar i alguns detalls a tenir en compte.

**CJT_DOCUMENTS:** (cjt_documents.jar)
- public Cjt_Documents(): Es pot provar directament en qualsevol funció. (Afegir, Modificar...)
- public Cjt_Documents(Document d): Es pot provar directament en qualsevol funció. (Afegir, Modificar...)
- public void

**DOCUMENTS:** (document.jar)
- private String correction(String s)
- public Document()
- public Document(Frase titol, Frase autor, Paraula tema)
- public Frase get_titol()
- public Frase get_autor()
- public Paraula get_tema()
- public Text get_contingut()
- public int get_id()
- public void set_titol(Frase titol)
- public void set_autor(Frase autor)
- public void set_tema(Paraula tema)
- public void set_contingut(Text contingut)
- public void set_titol_String(String s)
- public void set_autor_String(String s)
- public void set_contingut_string(String s)
- public void set_tema_String(String s)
- public void imprimir()
- public void set_all_data(String f)
- public void crear_divisions()




**TEXT:** (text.jar)
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

**FRASE:** (frase.jar)
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

**PARAULA:** (paraula.jar)
- public Paraula()
- public Paraula(String P)
- public String get_p()
- public void set_p()
- public void imprimir()
- public boolean is_stop_word()

**FITXER:** (fitxer.jar)

**ARXIU:** (arxiu.jar)

**ESPAI_VECTORIAL:** (espai_vectorial.jar)

**COMPARADOR:** (comparador.jar)

**VECTOR:** (vector.jar)

**REGISTRE:** (registre.jar)
