# Maintenance to UPCH 2020 "legacy stationery" software 🛠

## Content 📋

-  [Trello](https://trello.com/b/LCEY0tAQ)

-  [Requirements](https://docs.google.com/document/d/1TUnLOxqZ_Zlmv3QmgArtjHjJ0VBsFH5OscPsshVyW7E/edit?usp=sharing)

-  [JAR's](https://drive.google.com/drive/folders/1Gk_ZLiixr_vatw2aHaJLkwnyckHinNIu?usp=sharing)

-  [Technologies](#technologies-)

-  [Get this project](#get-this-project-)

-  [Configurations](#configurations-)

-  [Run](#run-)

-  [Authors](#authors-)

  

# Technologies 🛸

-  [Eclipse Java Developers](https://www.eclipse.org/downloads/packages/)

-  [JavaFX 11](https://adoptopenjdk.net/)

-  [MySQL](https://www.mysql.com/downloads/)

-  [FXML](https://openjfx.io/javadoc/11/javafx.fxml/javafx/fxml/doc-files/introduction_to_fxml.html)

-  [ITextPDF](http://www.java2s.com/Code/Jar/i/Downloaditextpdf510jar.htm)

-  [ControlsFX](https://www.youtube.com/redirect?v=SkXYg3M0hOQ&redir_token=QUFFLUhqa0xkT081a2ppVU1GWkhUY2p3dlp2WUJQQUhiZ3xBQ3Jtc0ttM1JsaXBVdVhqbERMVGFDSHlLakFxcXVoeGk4THlMa2VVcGRRY1ZCYjJVd3ZNckRMOEhwenB4S2ZDbjhHVlB3T085alRyTW1fUzQwZHhRYXVYUktianhCaVBScDFCZXJoVFc3aVFkUmNBS2VhaTVJSQ%3D%3D&event=video_description&q=http%3A%2F%2Ffxexperience.com%2Fdownloads%2Fcontrolsfx-8-40-14)

  

# Get this project 📥

-  [Git](https://git-scm.com/downloads) on your computer.

- In the CMD, locate yourself in the folder that you want to have the project.

- Put the command in the CMD.

	````
	git clone https://github.com/RocioValedoStgo/papeleria_legado.git
	````
  

# Configurations ⚙

#### Check this steps

- Step 1
	- Add JAR's in the ClassPath and ModulePath.
		- [JAR's](https://drive.google.com/drive/folders/1Gk_ZLiixr_vatw2aHaJLkwnyckHinNIu?usp=sharing)

- Step 2
	- Modify Main Class
		- > Run > Run configurations > Java App > Main > Main class:
		- Search Main of the project
- Step 3
	- Create a new `User Library` under.
		> Eclipse > Project > Properties > Java Build Path > Libraries > ModulePath > Add Library > User libraries > New
	- Give it a name.
	- Select the new library.
	- Add External JavaFX 11's JAR's.
	- Search and select JavaFX 11's JAR'S.
- Step 4
	- Create a new `variable`.
		> Run > Run configurations > Java App > Main (1) > arguments > Variables > Edit Variables > New
	- Give it a name.
	- Set the value with the environment variable PATH_TO_FX.
	- `Apply and close`.
- Step 5
	- Modify Execution.
		> Run > Run configurations > Java App > Main (1) > arguments > VM arguments
	- Set the value `--module-path ${name-variable-step-2} --add-modules javafx.controls,javafx.fxml --add-exports javafx.base/com.sun.javafx.event=ALL-UNNAMED`.
	
#### Or check this
[JavaFX and Eclipse.](https://openjfx.io/openjfx-docs/)

# Run ▶

  #### If the configuration has been correct, press the "Run Main (1)" button and you must start the program. 🤞🏼

# Authors 👨🏼‍💻

- [Salim Vazquez Solis](https://github.com/SalimVazquez)
- [Rocio Valedo Santiago](https://github.com/RocioValedoStgo)
