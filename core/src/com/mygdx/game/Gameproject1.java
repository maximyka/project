package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Gameproject1 extends ApplicationAdapter {
	public static final int SCR_WIDTH = 1280, SCR_HEIGHT = 720;
	int numImg;
	int numTool = 1, numBook = 1;

	int numListTool = 1;
	int numMonster = MathUtils.random(1, 2), numBullet = MathUtils.random(1, 3);
	int hpPlayer, atkPlayer;
	int hpPlayerInfoTool, atkPlayerInfoTool;
	int numNote, numNoteTap1, numNoteTap2, numNoteTap3, numNoteTap4;
	int numNoteNeed1, numNoteNeed2, numNoteNeed3, numNoteNeed4;
	int coins = 0, diamonds = 0, level = 1, location = 1;
	int numSkin, numPageSkins = 1;
	int numKill;
	long timeLastSpawnEnemy, timeIntervalSpawnEnemy = MathUtils.random(12000, 20000);
	boolean boughtSkin1, boughtSkin2, boughtSkin3, boughtSkin4, boughtSkin5;
	boolean boughtTool2, boughtTool3, boughtTool4, boughtTool5, boughtTool6;
	boolean onShit, onSlow, onSlowest, onPoison, onLongShit;
	boolean useHeal, useSlow, usePoison, useLongShit, useVamp;
	boolean soundOn = true, Final;
	public static boolean fazaTurelShoot;

	String nameNote = "";

	SpriteBatch batch;
	OrthographicCamera camera;
	Vector3 touchPos;

	BitmapFont font, fontLittle, fontBlack, fontBig, fontBigBlack, fontRussian;

	Texture imgPlay, imgFonUps;
	Texture imgButtonFight, imgButtonFightA, imgButtonSkins, imgButtonSkinsA, imgButtonSetting, imgButtonSettingA, imgButtonTools, imgButtonToolsA, imgButtonBooks, imgButtonBooksA,
			imgListRight, imgListLeft, imgChoiceComb, imgChoiceEffect;
	Texture imgInfoTool, imgInfoToolA;
	Texture imgListFight;
	Texture imgButtonFightFree, imgButtonFightReal, imgButtonFightMini;
	Texture imgBackListFight, imgButtonBack, imgButtonBack0, imgExit;
	Texture imgBoss;
	Texture imgMoveYou, imgMoveEnemy;
	Texture imgWKey, imgWAKey, imgBKey, imgBAKey, imgBKeyP, imgBAKeyP;
	Texture imgFonO, imgFonL, imgFonLP, imgFonLR, imgFonStart, imgFonLF;
	Texture imgCellBook, imgBookATK, imgBookATK2, imgBookATK3, imgBookATK4, imgBookATK5;
	Texture imgButtonSkin0, imgButtonSkin0A, imgButtonSkin1, imgButtonSkin1A, imgButtonSkin2, imgButtonSkin2A, imgButtonSkin3, imgButtonSkin3A, imgButtonSkin4, imgButtonSkin4A, imgButtonSkin5, imgButtonSkin5A;
	Texture imgToolIcon, imgSound, imgSoundOff;
	Texture imgMonster1atlas, imgMonster2atlas, imgMonster3atlas, imgMonster4atlas, imgTurelAtlas, imgBullet;
	Texture imgTablo;
	TextureRegion[][][] imgTurel = new TextureRegion[1][3][20];
	TextureRegion[][][] imgMonster = new TextureRegion[3][5][20]; //номер, состояние, фаза
	TextureRegion[][][] imgWallBreaker = new TextureRegion[1][3][20]; //номер, состояние, фаза

	public static final int STAY = 0, GO = 1, ATTACK = 2, DEATH = 3, GOOUT = 4;
	public static final int STAYTUREL = 0, ATTACKTUREL = 1, DEATHTUREL = 2;
	public static final int GOSKELET = 0, ATTACKSKELET = 1, GOOUTSKELET = 2;


	Button buttonTools, buttonBooks, buttonListLeft, buttonListRight, buttonListLeftSkins, buttonListRightSkins,
			buttonFightFree, buttonFightReal, buttonFightMini,
			buttonBackListFight, buttonBack,
			exitYes, exitNo,
			bookATK, bookHP,
			buttonSkin0, buttonSkin1, buttonSkin2, buttonSkin3, buttonSkin4, buttonSkin5,
			buttonSet0, buttonSet1, buttonSet2,
			buttonSound;
	Button buttonC, buttonDb, buttonD, buttonEb, buttonE, buttonF, buttonGb, buttonG, buttonAb, buttonA, buttonBb, buttonB, buttonC2, buttonDb2;
	ButtonCircle buttonFight, buttonSkins, buttonSetting;
	Button buttonInfoTool;

	Sound sndC, sndDb, sndD, sndEb, sndE, sndF, sndGb, sndG, sndAb, sndA, sndBb, sndB, sndC2, sndDb2,
			sndHitEnemy, sndHitHero, sndBuy, sndKesh, sndClose;
	Music mscUps, mscFree, mscFight;

	boolean listTools = true, listFight, listSettings, listSkins, listSets;
	boolean isHitKeyC, isHitKeyDb, isHitKeyD, isHitKeyEb, isHitKeyE, isHitKeyF, isHitKeyGb, isHitKeyG,
			isHitKeyAb, isHitKeyA, isHitKeyBb, isHitKeyB, isHitKeyC2, isHitKeyDb2;
	public static boolean exit, game;

	static float sec = 0f;
	Turel turel;
	Array<Bullet> bullet = new Array<>();
	Array<Monster> monster = new Array<>();
	Array<WallBreaker> wallBreaker = new Array<>();

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
		touchPos = new Vector3();

		imgPlay = new Texture("play.png");
		imgFonUps = new Texture("fonUps.png");
		imgButtonFight = new Texture("buttonFight.png");
		imgButtonFightA = new Texture("buttonFightA.png");
		imgButtonSkins = new Texture("buttonSkins.png");
		imgButtonSkinsA = new Texture("buttonSkinsA.png");
		imgButtonSetting = new Texture("buttonSettings.png");
		imgButtonSettingA = new Texture("buttonSettingsA.png");
		imgButtonTools = new Texture("buttonTools.png");
		imgButtonToolsA = new Texture("buttonToolsA.png");
		imgButtonBooks = new Texture("buttonBooks.png");
		imgButtonBooksA = new Texture("buttonBooksA.png");
		imgListLeft = new Texture("listLeft.png");
		imgListRight = new Texture("listRight.png");
		imgInfoTool = new Texture("infoTool.png");
		imgInfoToolA = new Texture("infoToolA.png");
		imgChoiceComb = new Texture("choiceComb.png");
		imgChoiceEffect = new Texture("choiceEffect.png");
		imgListFight = new Texture("listFight.png");
		imgButtonFightFree = new Texture("buttonFightFree.png");
		imgButtonFightReal = new Texture("buttonFightReal.png");
		imgButtonFightMini = new Texture("buttonFightMini.png");
		imgBackListFight = new Texture("buttonBackListFight.png");
		imgButtonBack = new Texture("кнопкаВыйти1Л.png");
		imgButtonBack0 = new Texture("кнопкаНазад.png");
		imgExit = new Texture("выйти.png");
		imgBookATK = new Texture("bookATK.png");
		imgBookATK2 = new Texture("bookATK2.png");
		imgBookATK3 = new Texture("bookATK3.png");
		imgBookATK4 = new Texture("bookATK4.png");
		imgBookATK5 = new Texture("bookATK5.png");
		imgCellBook = new Texture("cellBook.png");
		imgButtonSkin0 = new Texture("buttonSkin0.png");
		imgButtonSkin0A = new Texture("buttonSkin0A.png");
		imgButtonSkin1 = new Texture("buttonSkin1.png");
		imgButtonSkin1A = new Texture("buttonSkin1A.png");
		imgButtonSkin2 = new Texture("buttonSkin2.png");
		imgButtonSkin2A = new Texture("buttonSkin2A.png");
		imgButtonSkin3 = new Texture("buttonSkin3.png");
		imgButtonSkin3A = new Texture("buttonSkin3A.png");
		imgButtonSkin4 = new Texture("buttonSkin4.png");
		imgButtonSkin4A = new Texture("buttonSkin4A.png");
		imgButtonSkin5 = new Texture("buttonSkin5.png");
		imgButtonSkin5A = new Texture("buttonSkin5A.png");
		imgFonO = new Texture("фонСвобода.png");
		imgFonStart = new Texture("FonStart.png");
		imgFonLF = new Texture("FonLF.png");
		imgFonLR = new Texture("fonL1R.png");

		imgMoveYou = new Texture("ходГероя.png");
		imgMoveEnemy = new Texture("ходВрага.png");
		imgSound = new Texture("buttonSound.png");
		imgSoundOff = new Texture("buttonSoundOff.png");

		imgMonster1atlas = new Texture("monster1atlas.png");
		imgMonster2atlas = new Texture("monster2atlas.png");
		imgMonster3atlas = new Texture("monster3atlas.png");
		imgMonster4atlas = new Texture("monster4atlas.png");
		imgTurelAtlas = new Texture("turelAtlas.png");
		imgBullet = new Texture("bullet.png");
		imgTablo = new Texture("tablo.png");

		for (int i=0; i<10; i++) {
			imgMonster[0][STAY][i] = new TextureRegion(imgMonster1atlas, 0, 0, 330, 330);
			imgMonster[0][GO][i] = new TextureRegion(imgMonster1atlas, i * 330, 0, 330, 330);
			imgMonster[0][GO][i+10] = new TextureRegion(imgMonster1atlas, i * 330, 330, 330, 330);
			imgMonster[0][ATTACK][i] = new TextureRegion(imgMonster1atlas, i * 330, 660, 330, 330);
			imgMonster[0][DEATH][i] = new TextureRegion(imgMonster1atlas, i * 330, 990, 330, 330);
			imgMonster[0][GOOUT][i] = new TextureRegion(imgMonster1atlas, 3300-330, 990, 330, 330);
		}
		for (int i=0; i<10; i++) {
			imgMonster[1][STAY][i] = new TextureRegion(imgMonster2atlas, 0, 0, 330, 330);
			imgMonster[1][GO][i] = new TextureRegion(imgMonster2atlas, i * 330, 0, 330, 330);
			imgMonster[1][GO][i+10] = new TextureRegion(imgMonster2atlas, i * 330, 330, 330, 330);
			imgMonster[1][ATTACK][i] = new TextureRegion(imgMonster2atlas, i * 330, 660, 330, 330);
			imgMonster[1][DEATH][i] = new TextureRegion(imgMonster2atlas, i * 330, 990, 330, 330);
			imgMonster[1][GOOUT][i] = new TextureRegion(imgMonster2atlas, 3300-330, 990, 330, 330);
		}
		for (int i=0; i<10; i++) {
			imgWallBreaker[0][GOSKELET][i] = new TextureRegion(imgMonster4atlas, i * 660, 0, 660, 660);
			imgWallBreaker[0][GOSKELET][i+10] = new TextureRegion(imgMonster4atlas, i * 660, 660, 660, 660);
			imgWallBreaker[0][ATTACKSKELET][i] = new TextureRegion(imgMonster4atlas, i * 660, 1320, 660, 660);
			imgWallBreaker[0][GOOUTSKELET][i] = new TextureRegion(imgMonster4atlas, 6600-660, 1320, 660, 660);
		}
		for (int i=0; i<10; i++) {
			imgTurel[0][STAYTUREL][i] = new TextureRegion(imgTurelAtlas, 0, 0, 330, 330);
			imgTurel[0][ATTACKTUREL][i] = new TextureRegion(imgTurelAtlas, i * 330, 0, 330, 330);
			imgTurel[0][ATTACKTUREL][i+10] = new TextureRegion(imgTurelAtlas, i * 330, 330, 330, 330);
			imgTurel[0][DEATHTUREL][i] = new TextureRegion(imgTurelAtlas, i * 330, 660, 330, 330);
		}

		mscUps = Gdx.audio.newMusic(Gdx.files.internal("mscUps.mp3"));
		mscFree = Gdx.audio.newMusic(Gdx.files.internal("mscFree.mp3"));
		mscFight = Gdx.audio.newMusic(Gdx.files.internal("1fight.wav"));

		sndHitEnemy = Gdx.audio.newSound(Gdx.files.internal("sndHitEnemy.mp3"));
		sndHitHero = Gdx.audio.newSound(Gdx.files.internal("sndHitHero.mp3"));
		sndBuy = Gdx.audio.newSound(Gdx.files.internal("sndBuy.mp3"));
		sndClose = Gdx.audio.newSound(Gdx.files.internal("sndClose.mp3"));
		sndKesh = Gdx.audio.newSound(Gdx.files.internal("sndKesh.mp3"));

		defineObjects();
		defineToolInfa();

		buttonFight = new ButtonCircle(SCR_WIDTH/2-300/2,-130,300,300);
		buttonSkins = new ButtonCircle(-120,-130,300,300);
		buttonSetting = new ButtonCircle(SCR_WIDTH-(300-120),-130,300,300);
		buttonTools = new Button(20,SCR_HEIGHT-90,281,59);
		buttonBooks = new Button(325,SCR_HEIGHT-90,281,59);
		buttonListLeft = new Button(200,150,75,99);
		buttonListRight = new Button(400,150,75,99);
		buttonListLeftSkins = new Button(800,450,75,99);
		buttonListRightSkins = new Button(950,450,75,99);
		buttonSkin0 = new Button(270, 180, 200, 200);
		buttonSkin1 = new Button(535, 180, 200, 200);
		buttonSkin2 = new Button(800, 180, 200, 200);
		buttonSkin3 = new Button(270, 180, 200, 200);
		buttonSkin4 = new Button(535, 180, 200, 200);
		buttonSkin5 = new Button(800, 180, 200, 200);
		buttonSet0 = new Button(270, 200, 200, 200);
		buttonSet1 = new Button(535, 200, 200, 200);
		buttonSet2 = new Button(800, 200, 200, 200);

		buttonFightFree = new Button(270, 230,200,200);
		buttonFightReal = new Button(535, 230,200,200);
		buttonFightMini = new Button(800, 230,200,200);
		buttonBackListFight = new Button(240, 460,221,84);
		buttonBack = new Button(20, 570,133,133);
		exitNo = new Button(600, 200, 400, 50);
		exitYes = new Button(600, 315, 400, 50);
		bookATK = new Button(90, 350, 150, 190);
		bookHP = new Button(75, 350, 150, 190);

		buttonC = new Button(5, 5, 157, 137);
		buttonDb = new Button(97, 142, 130, 137);
		buttonD = new Button(162, 5, 157, 137);
		buttonEb = new Button(254, 142, 130, 137);
		buttonE = new Button(319, 5, 157, 137);
		buttonF = new Button(476, 5, 157, 137);
		buttonGb = new Button(568, 142, 130, 137);
		buttonG = new Button(633, 5, 157, 137);
		buttonAb = new Button(725, 142, 130, 137);
		buttonA = new Button(791, 5, 157, 137);
		buttonBb = new Button(882, 142, 130, 137);
		buttonB = new Button(948, 5, 157, 137);
		buttonC2 = new Button(1105, 5, 157, 137);
		buttonDb2 = new Button(1184, 142, 130, 137);

		buttonInfoTool = new Button(54, 320, 541, 201);
		buttonSound = new Button(700, 200, 319, 319);

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("17807.otf"));
		FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("20694.otf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter parameter3 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter parameter4 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter parameter5 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter parameter6 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.color = new Color(1, 1, 1, 1);
		parameter2.color = new Color(1, 1, 1, 1);
		parameter3.color = new Color(0, 0, 0, 1);
		parameter4.color = new Color(0, 0, 0, 1);
		parameter5.color = new Color(0, 0, 0, 1);
		parameter6.color = new Color(0, 0, 0, 1);
		parameter.size = 55;
		parameter2.size = 40;
		parameter3.size = 40;
		parameter4.size = 60;
		parameter5.size = parameter6.size = 30;
		parameter.characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
		parameter6.characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
		font = generator.generateFont(parameter);
		fontLittle = generator.generateFont(parameter2);
		fontBlack = generator.generateFont(parameter3);
		fontBig = generator.generateFont(parameter4);
		fontBigBlack = generator.generateFont(parameter5);
		fontRussian = generator2.generateFont(parameter6);
		generator.dispose();
		generator2.dispose();

		hpPlayer = 100;
		atkPlayer = 10;
		if (soundOn) {
			mscUps.setLooping(true);
			mscUps.play();
		}
		turel = new Turel(50, SCR_HEIGHT/2-100, 330, 330, 100, 10);
	}
	@Override
	public void render () {
		if (Gdx.input.justTouched()) {
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);

			float tx = touchPos.x;
			float ty = touchPos.y;

			if (numImg == 0) numImg = 1;
			else if (buttonFight.isHit(tx, ty) && numImg == 1) listFight = true;
			else if (buttonSetting.isHit(tx, ty) && numImg == 1) listSettings = true;
			else if (buttonListLeft.isHit(tx, ty) && numImg == 1) {
				if (listTools) {
					numListTool--;
					if (numListTool == 0) numListTool = 1;
					defineToolInfa();
				} else {
					numBook--;
					if (numBook == 0) numBook = 1;
				}
			} else if (buttonListRight.isHit(tx, ty) && numImg == 1) {
				if (listTools) {
					numListTool++;
					if (numListTool == 7) numListTool = 6;
					defineToolInfa();
				} else {
					numBook++;
					if (numBook == 4) numBook = 3;
				}
			} else if (buttonBooks.isHit(tx, ty) && numImg == 1) listTools = false;
			else if (buttonTools.isHit(tx, ty) && numImg == 1) listTools = true;
			else if (buttonBackListFight.isHit(tx, ty) && numImg == 1 && (listFight | listSkins | listSets | listSettings)) {
				if (listFight) listFight = false;
				else if (listSettings) listSettings = false;
				else if (listSkins) {
					numPageSkins = 1;
					listSkins = false;
				} else listSets = false;
			}
			else if (buttonSkins.isHit(tx, ty) && numImg == 1) listSkins = true;
			else if (buttonListLeftSkins.isHit(tx, ty) && listSkins) {
				numPageSkins--;
				if (numPageSkins == 0) numPageSkins = 1;
			}
			else if (buttonListRightSkins.isHit(tx, ty) && listSkins) {
				numPageSkins++;
				if (numPageSkins == 3) numPageSkins = 2;
			}
			else if (buttonSound.isHit(tx, ty) && numImg == 1 && listSettings && soundOn) {
				soundOn = false;
				mscUps.stop();
				mscUps.setLooping(false);
			}
			else if (buttonSound.isHit(tx, ty) && numImg == 1 && listSettings && !soundOn) {
				soundOn = true;
				mscUps.setLooping(true);
				mscUps.play();
			}
			else if (buttonInfoTool.isHit(tx, ty) && numImg == 1 && !listFight && !listSkins && listTools){
				if (numListTool == 1) numTool = 1;
				else if (numListTool == 2 && boughtTool2) numTool = 2;
				else if (numListTool == 2 && !boughtTool2){
					if (coins >= 20) {
						coins -= 20;
						boughtTool2 = true;
						sndBuy.play();
					} else sndClose.play();
				}
				else if (numListTool == 3 && boughtTool3) numTool = 3;
				else if (numListTool == 3 && !boughtTool3){
					if (coins >= 50) {
						coins -= 50;
						boughtTool3 = true;
						sndBuy.play();
					} else sndClose.play();
				}
				else if (numListTool == 4 && boughtTool4) numTool = 4;
				else if (numListTool == 4 && !boughtTool4){
					if (coins >= 100) {
						coins -= 100;
						boughtTool4 = true;
						sndBuy.play();
					} else sndClose.play();
				}
				else if (numListTool == 5 && boughtTool5) numTool = 5;
				else if (numListTool == 5 && !boughtTool5){
					if (coins >= 150) {
						coins -= 150;
						boughtTool5 = true;
						sndBuy.play();
					} else sndClose.play();
				}
				else if (numListTool == 6 && boughtTool6) numTool = 6;
				else if (numListTool == 6 && !boughtTool6){
					if (coins >= 300) {
						coins -= 300;
						boughtTool6 = true;
						sndBuy.play();
					} else sndClose.play();
				}
				defineTool();
			}

			else if (buttonSkin0.isHit(tx, ty) && listSkins && numPageSkins == 1) numSkin = 0;
			else if (buttonSkin1.isHit(tx, ty) && listSkins && numPageSkins == 1) {
				if (boughtSkin1) numSkin = 1;
				else if (diamonds >= 6) {
					diamonds -= 6;
					boughtSkin1 = true;
				}
			}
			else if (buttonSkin2.isHit(tx, ty) && listSkins && numPageSkins == 1) {
				if (boughtSkin2) numSkin = 2;
				else if (diamonds >= 6) {
					diamonds -= 6;
					boughtSkin2 = true;
				}
			}
			else if (buttonSkin3.isHit(tx, ty) && listSkins && numPageSkins == 2) {
				if (boughtSkin3) numSkin = 3;
				else if (diamonds >= 6) {
					diamonds -= 6;
					boughtSkin3 = true;
				}
			}
			else if (buttonSkin4.isHit(tx, ty) && listSkins && numPageSkins == 2) {
				if (boughtSkin4) numSkin = 4;
				else if (diamonds >= 6) {
					diamonds -= 6;
					boughtSkin4 = true;
				}
			}
			else if (buttonSkin5.isHit(tx, ty) && listSkins && numPageSkins == 2) {
				if (boughtSkin5) numSkin = 5;
				else if (diamonds >= 6) {
					diamonds -= 6;
					boughtSkin5 = true;
				}
			}

			else if (buttonBack.isHit(tx, ty) && (numImg == 2 | numImg == 3)) exit = true;
			else if (buttonFightReal.isHit(tx, ty) && listFight) {
				numKill=0;
				numBullet = MathUtils.random(1, 3);
				numNoteNeed1 = MathUtils.random(1, 14);
				numNoteNeed2 = MathUtils.random(1, 14);
				numNoteNeed3 = MathUtils.random(1, 14);
				numNoteNeed4 = MathUtils.random(1, 14);
				listFight = false;
				numImg = 2;
				if (level >= 1 && level <= 4) numMonster = MathUtils.random(1, 5);
				else if (level >= 6 && level <= 9) numMonster = MathUtils.random(7, 11);
				else if (level >= 11 && level <= 14) numMonster = MathUtils.random(13, 17);
				defineObjects();
				defineToolInfa();
				defineTool();
				mscUps.setLooping(false);
				mscUps.stop();
				if (soundOn){
					mscFight.setLooping(true);
					mscFight.play();
				}
			} else if (buttonFightFree.isHit(tx, ty) && listFight) {
				numImg = 3;
				defineObjects();
				listFight = false;
				mscUps.setLooping(false);
				mscUps.stop();
				if (soundOn){
					mscFree.setLooping(true);
					mscFree.play();
				}
			} else if (exitYes.isHit(tx, ty) && exit) {
				turel.life = 100;
				turel.faza = 0;
				turel.state = STAYTUREL;
				turel.isAlive = true;
				monster.clear();
				wallBreaker.clear();
				timeLastSpawnEnemy = 0;
				defineToolInfa();
				defineTool();
				onLongShit = false;
				onSlow = false;
				onSlowest = false;
				onShit = false;
				onPoison = false;
				numImg = 1;
				if (soundOn) {
					mscUps.setLooping(true);
					mscUps.play();
				}
				mscFree.setLooping(false);
				mscFree.stop();
				mscFight.setLooping(false);
				mscFight.stop();
				sec = 0f;
				numNoteTap1 = numNoteTap2 = numNoteTap3 = numNoteTap4 = numNote = 0;
				game = false;
				exit = false;
				if (level != 1 && level != 6 &&  level != 11 && level != 16 && level != 21 && level != 26
						&& level != 31 && level != 36 && level != 41) coins += level * 5;
				if (location == 1) level = 1;
				else if (location == 2) level = 6;
				else if (location == 3) level = 11;
			} else if (exitNo.isHit(tx, ty) && exit) exit = false;

			isHitKeyC = buttonC.isHit(tx, ty) && game;
			isHitKeyDb = buttonDb.isHit(tx, ty) && game;
			isHitKeyD = buttonD.isHit(tx, ty) && game;
			isHitKeyEb = buttonEb.isHit(tx, ty) && game;
			isHitKeyE = buttonE.isHit(tx, ty) && game;
			isHitKeyF = buttonF.isHit(tx, ty) && game;
			isHitKeyGb = buttonGb.isHit(tx, ty) && game;
			isHitKeyG = buttonG.isHit(tx, ty) && game;
			isHitKeyAb = buttonAb.isHit(tx, ty) && game;
			isHitKeyA = buttonA.isHit(tx, ty) && game;
			isHitKeyBb = buttonBb.isHit(tx, ty) && game;
			isHitKeyB = buttonB.isHit(tx, ty) && game;
			isHitKeyC2 = buttonC2.isHit(tx, ty) && game;
			isHitKeyDb2 = buttonDb2.isHit(tx, ty) && game;

			if (buttonC.isHit(tx, ty) && game && !exit) {
				sndC.play();
				if (numImg == 2) numNote++;
				if (numNoteTap1 == 0) numNoteTap1 = 1;
				else if (numNoteTap2 == 0) numNoteTap2 = 1;
				else if (numNoteTap3 == 0) numNoteTap3 = 1;
				else if (numNoteTap4 == 0) numNoteTap4 = 1;
			} else if (buttonDb.isHit(tx, ty) && game && !exit) {
				sndDb.play();
				if (numImg == 2) numNote++;
				if (numNoteTap1 == 0) numNoteTap1 = 2;
				else if (numNoteTap2 == 0) numNoteTap2 = 2;
				else if (numNoteTap3 == 0) numNoteTap3 = 2;
				else if (numNoteTap4 == 0) numNoteTap4 = 2;
			} else if (buttonD.isHit(tx, ty) && game && !exit) {
				sndD.play();
				if (numImg == 2) numNote++;
				if (numNoteTap1 == 0) numNoteTap1 = 3;
				else if (numNoteTap2 == 0) numNoteTap2 = 3;
				else if (numNoteTap3 == 0) numNoteTap3 = 3;
				else if (numNoteTap4 == 0) numNoteTap4 = 3;
			} else if (buttonEb.isHit(tx, ty) && game && !exit) {
				sndEb.play();
				if (numImg == 2) numNote++;
				if (numNoteTap1 == 0) numNoteTap1 = 4;
				else if (numNoteTap2 == 0) numNoteTap2 = 4;
				else if (numNoteTap3 == 0) numNoteTap3 = 4;
				else if (numNoteTap4 == 0) numNoteTap4 = 4;
			} else if (buttonE.isHit(tx, ty) && game && !exit) {
				sndE.play();
				if (numImg == 2) numNote++;
				if (numNoteTap1 == 0) numNoteTap1 = 5;
				else if (numNoteTap2 == 0) numNoteTap2 = 5;
				else if (numNoteTap3 == 0) numNoteTap3 = 5;
				else if (numNoteTap4 == 0) numNoteTap4 = 5;
			} else if (buttonF.isHit(tx, ty) && game && !exit) {
				sndF.play();
				if (numImg == 2) numNote++;
				if (numNoteTap1 == 0) numNoteTap1 = 6;
				else if (numNoteTap2 == 0) numNoteTap2 = 6;
				else if (numNoteTap3 == 0) numNoteTap3 = 6;
				else if (numNoteTap4 == 0) numNoteTap4 = 6;
			} else if (buttonGb.isHit(tx, ty) && game && !exit) {
				sndGb.play();
				if (numImg == 2) numNote++;
				if (numNoteTap1 == 0) numNoteTap1 = 7;
				else if (numNoteTap2 == 0) numNoteTap2 = 7;
				else if (numNoteTap3 == 0) numNoteTap3 = 7;
				else if (numNoteTap4 == 0) numNoteTap4 = 7;
			} else if (buttonG.isHit(tx, ty) && game && !exit) {
				sndG.play();
				if (numImg == 2) numNote++;
				if (numNoteTap1 == 0) numNoteTap1 = 8;
				else if (numNoteTap2 == 0) numNoteTap2 = 8;
				else if (numNoteTap3 == 0) numNoteTap3 = 8;
				else if (numNoteTap4 == 0) numNoteTap4 = 8;
			} else if (buttonAb.isHit(tx, ty) && game && !exit) {
				sndAb.play();
				if (numImg == 2) numNote++;
				if (numNoteTap1 == 0) numNoteTap1 = 9;
				else if (numNoteTap2 == 0) numNoteTap2 = 9;
				else if (numNoteTap3 == 0) numNoteTap3 = 9;
				else if (numNoteTap4 == 0) numNoteTap4 = 9;
			} else if (buttonA.isHit(tx, ty) && game && !exit) {
				sndA.play();
				if (numImg == 2) numNote++;
				if (numNoteTap1 == 0) numNoteTap1 = 10;
				else if (numNoteTap2 == 0) numNoteTap2 = 10;
				else if (numNoteTap3 == 0) numNoteTap3 = 10;
				else if (numNoteTap4 == 0) numNoteTap4 = 10;
			} else if (buttonBb.isHit(tx, ty) && game && !exit) {
				sndBb.play();
				if (numImg == 2) numNote++;
				if (numNoteTap1 == 0) numNoteTap1 = 11;
				else if (numNoteTap2 == 0) numNoteTap2 = 11;
				else if (numNoteTap3 == 0) numNoteTap3 = 11;
				else if (numNoteTap4 == 0) numNoteTap4 = 11;
			} else if (buttonB.isHit(tx, ty) && game && !exit) {
				sndB.play();
				if (numImg == 2) numNote++;
				if (numNoteTap1 == 0) numNoteTap1 = 12;
				else if (numNoteTap2 == 0) numNoteTap2 = 12;
				else if (numNoteTap3 == 0) numNoteTap3 = 12;
				else if (numNoteTap4 == 0) numNoteTap4 = 12;
			} else if (buttonC2.isHit(tx, ty) && game && !exit) {
				sndC2.play();
				if (numImg == 2) numNote++;
				if (numNoteTap1 == 0) numNoteTap1 = 13;
				else if (numNoteTap2 == 0) numNoteTap2 = 13;
				else if (numNoteTap3 == 0) numNoteTap3 = 13;
				else if (numNoteTap4 == 0) numNoteTap4 = 13;
			} else if (buttonDb2.isHit(tx, ty) && game && !exit) {
				sndDb2.play();
				if (numImg == 2) numNote++;
				if (numNoteTap1 == 0) numNoteTap1 = 14;
				else if (numNoteTap2 == 0) numNoteTap2 = 14;
				else if (numNoteTap3 == 0) numNoteTap3 = 14;
				else if (numNoteTap4 == 0) numNoteTap4 = 14;
			}
			if (numNote >= 4) {
				numNote = 0;
				if (numNoteTap1 == numNoteNeed1 && numNoteTap2 == numNoteNeed2 && numNoteTap3 == numNoteNeed3 && numNoteTap4 == numNoteNeed4) {
					fazaTurelShoot = true;
					bullet.add(new Bullet(150, 490, 100, 10));
				}
				numNoteNeed1 = MathUtils.random(1, 14);
				numNoteNeed2 = MathUtils.random(1, 14);
				numNoteNeed3 = MathUtils.random(1, 14);
				numNoteNeed4 = MathUtils.random(1, 14);
				numNoteTap1 = numNoteTap2 = numNoteTap3 = numNoteTap4 = 0;
				numBullet = MathUtils.random(1, 3);
			}
		}

		if (numImg == 2) {
			if (turel.life <= 0 && turel.faza >= 9) {
				turel.life = 100;
				turel.faza = 0;
				coins += numKill*5;
				diamonds += numKill;
				numKill = 0;
				turel.state = STAYTUREL;
				turel.isAlive = true;
				monster.clear();
				wallBreaker.clear();
				timeLastSpawnEnemy = 0;
				defineToolInfa();
				defineTool();
				onLongShit = false;
				onSlow = false;
				onSlowest = false;
				onShit = false;
				onPoison = false;
				numImg = 1;
				if (soundOn) {
					mscUps.setLooping(true);
					mscUps.play();
				}
				mscFree.setLooping(false);
				mscFree.stop();
				mscFight.setLooping(false);
				mscFight.stop();
				sec = 0f;
				numNoteTap1 = numNoteTap2 = numNoteTap3 = numNoteTap4 = numNote = 0;
				game = false;
				exit = false;
				if (level != 1 && level != 6 &&  level != 11 && level != 16 && level != 21 && level != 26
						&& level != 31 && level != 36 && level != 41) coins += level * 5;
				if (location == 1) level = 1;
				else if (location == 2) level = 6;
				else if (location == 3) level = 11;
			}
		}

		if (numImg == 1) {
			onLongShit = false;
			onSlow = false;
			onSlowest = false;
			onShit = false;
			onPoison = false;
			useVamp = false;
			useLongShit = false;
			usePoison = false;
			useHeal = false;
			useSlow = false;
		}

		for(int i=0; i<bullet.size; i++){
			for(int j=0; j<monster.size; j++) {
				if (bullet.get(i).x + bullet.get(i).width >= monster.get(j).x && bullet.get(i).isAlive && monster.get(j).isAlive) {
					bullet.get(i).isAlive = false;
					if (numBullet == 1 && (monster.get(j).x - (turel.x + turel.width) < 1280))
						monster.get(j).life -= atkPlayer;
					else if (numBullet == 2) {
						if (monster.get(j).x - (turel.x + turel.width) < 301)
							monster.get(j).life -= atkPlayer * 4;
						else if (monster.get(j).x - (turel.x + turel.width) >= 301 && monster.get(j).x - (turel.x + turel.width) < 500)
							monster.get(j).life -= atkPlayer * 2;
						else if (monster.get(j).x - (turel.x + turel.width) >= 500 && monster.get(j).x - (turel.x + turel.width) < 700)
							monster.get(j).life -= atkPlayer;
						else if (monster.get(j).x - (turel.x + turel.width) >= 700 && monster.get(j).x - (turel.x + turel.width) < 1000)
							monster.get(j).life -= atkPlayer * 0.5;
						else if (monster.get(j).x - (turel.x + turel.width) >= 1000 && monster.get(j).x - (turel.x + turel.width) < 1280)
							monster.get(j).life -= atkPlayer * 0.1;
					} else if (numBullet == 3) {
						if (MathUtils.random(1, 2) == 1) monster.get(j).life -= atkPlayer * 4;
					}
					if (monster.get(j).life <= 0) {
						monster.get(j).isAlive = false;
						monster.get(j).state = DEATH;
						numKill++;
					}
				}
			}
		}

		batch.begin();
		if (numImg == 0) {
			batch.draw(imgFonStart, 0, 0);
			font.draw(batch, "Tap to continue", 50, 80);
		}
		else if (numImg == 1){
			game = false;

			batch.draw(imgFonUps, 0, 0);

			if (listFight) batch.draw(imgButtonFightA, buttonFight.x, buttonFight.y); else batch.draw(imgButtonFight, buttonFight.x, buttonFight.y);
			if (listSkins) batch.draw(imgButtonSkinsA, buttonSkins.x, buttonSkins.y); else batch.draw(imgButtonSkins, buttonSkins.x, buttonSkins.y);
			if (listSettings) batch.draw(imgButtonSettingA, buttonSetting.x, buttonSetting.y); else batch.draw(imgButtonSetting, buttonSetting.x, buttonSetting.y);

			if (listTools) {
				batch.draw(imgButtonToolsA, buttonTools.x, buttonTools.y);
				batch.draw(imgButtonBooks, buttonBooks.x, buttonBooks.y);
			} else {
				batch.draw(imgButtonTools, buttonTools.x, buttonTools.y);
				batch.draw(imgButtonBooksA, buttonBooks.x, buttonBooks.y);
			}

			batch.draw(imgListLeft, buttonListLeft.x, buttonListLeft.y);
			batch.draw(imgListRight, buttonListRight.x, buttonListRight.y);

			if (numListTool == numTool && listTools) batch.draw(imgInfoToolA, 54, 320);
			else batch.draw(imgInfoTool, 54, 320);

			font.draw(batch, "Tool", 110, 685);
			font.draw(batch, "Book", 405, 685);

			font.draw(batch, hpPlayer+" strength", 800, 650);
			font.draw(batch, atkPlayer+" damage", 800, 550);
			font.draw(batch, location+" location", 800, 450);
			font.draw(batch, coins+" coins", 800, 350);
			font.draw(batch, diamonds+" diamonds", 800, 250);

			if (listTools) batch.draw(imgToolIcon, 55, 320);
			else {
				if (numBook == 1)batch.draw(imgBookATK, bookATK.x, bookATK.y+25);
				else if (numBook == 2)batch.draw(imgBookATK2, bookHP.x+15, bookHP.y+30);
				else if (numBook == 3)batch.draw(imgBookATK4, bookHP.x+15, bookHP.y+30);
			}
			if (listTools){
				fontLittle.draw(batch,hpPlayerInfoTool+" strength", 285, 480);
				fontLittle.draw(batch,atkPlayerInfoTool+" damage", 300, 400);
				if (numListTool == 2 && !boughtTool2) font.draw(batch,20+" coins", 240, 590);
				else if (numListTool == 2 && boughtTool2) font.draw(batch," ", 270, 580);
				else if (numListTool == 3 && !boughtTool3) font.draw(batch,50+" coins", 240, 590);
				else if (numListTool == 3 && boughtTool3) font.draw(batch," ", 270, 580);
				else if (numListTool == 4 && !boughtTool4) font.draw(batch,100+" coins", 240, 590);
				else if (numListTool == 4 && boughtTool4) font.draw(batch," ", 270, 580);
				else if (numListTool == 5 && !boughtTool5) font.draw(batch,150+" coins", 240, 590);
				else if (numListTool == 5 && boughtTool5) font.draw(batch," ", 270, 580);
				else if (numListTool == 6 && !boughtTool6) font.draw(batch,300+" coins", 240, 590);
				else if (numListTool == 6 && boughtTool6) font.draw(batch," ", 270, 580);
			} else {
				if (numBook == 1) {
					fontLittle.draw(batch, "Attack", 330, 440);
				} else if (numBook == 2) {
					fontLittle.draw(batch, "Close Attack", 280, 440);
				} else if (numBook == 3) {
					fontLittle.draw(batch, "50%", 360, 460);
					fontLittle.draw(batch, "Strong Attack", 265, 420);
				}
			}
			if (listFight) {
				batch.draw(imgListFight, 200, 150);
				batch.draw(imgButtonFightFree, buttonFightFree.x, buttonFightFree.y);
				batch.draw(imgButtonFightReal, buttonFightReal.x, buttonFightReal.y);
				batch.draw(imgButtonFightMini, buttonFightMini.x, buttonFightMini.y);
				batch.draw(imgBackListFight, buttonBackListFight.x, buttonBackListFight.y);
				font.draw(batch, "Fight", 580, 530);
				fontLittle.draw(batch, "Train", 326, 220);
				fontLittle.draw(batch, "Story", 590, 220);
				fontLittle.draw(batch, "Modes", 845, 220);
			}
			else if (listSettings){
				batch.draw(imgListFight, 200, 150);
				batch.draw(imgBackListFight, buttonBackListFight.x, buttonBackListFight.y);
				if (soundOn) batch.draw(imgSound, buttonSound.x, buttonSound.y); else batch.draw(imgSoundOff, buttonSound.x, buttonSound.y);
			}
			else if (listSkins) {
				batch.draw(imgListFight, 200, 150);
				if (numPageSkins == 1){
					if (numSkin == 0)batch.draw(imgButtonSkin0A, buttonSkin0.x, buttonSkin0.y); else batch.draw(imgButtonSkin0, buttonSkin0.x, buttonSkin0.y);
					if (numSkin == 1)batch.draw(imgButtonSkin1A, buttonSkin1.x, buttonSkin1.y); else batch.draw(imgButtonSkin1, buttonSkin1.x, buttonSkin1.y);
					if (numSkin == 2)batch.draw(imgButtonSkin2A, buttonSkin2.x, buttonSkin2.y); else batch.draw(imgButtonSkin2, buttonSkin2.x, buttonSkin2.y);
					if (!boughtSkin1) fontLittle.draw(batch, "6 diamond", buttonSkin1.x+5, buttonSkin1.y+260);
					else fontLittle.draw(batch, "", buttonSkin1.x+5, buttonSkin1.y+260);
					if (!boughtSkin2) fontLittle.draw(batch, "6 diamond", buttonSkin2.x+5, buttonSkin2.y+260);
					else fontLittle.draw(batch, "", buttonSkin2.x+5, buttonSkin2.y+260);
				}
				else if (numPageSkins == 2){
					if (numSkin == 3)batch.draw(imgButtonSkin3A, buttonSkin3.x, buttonSkin3.y); else batch.draw(imgButtonSkin3, buttonSkin3.x, buttonSkin3.y);
					if (numSkin == 4)batch.draw(imgButtonSkin4A, buttonSkin4.x, buttonSkin4.y); else batch.draw(imgButtonSkin4, buttonSkin4.x, buttonSkin4.y);
					if (numSkin == 5)batch.draw(imgButtonSkin5A, buttonSkin5.x, buttonSkin5.y); else batch.draw(imgButtonSkin5, buttonSkin5.x, buttonSkin5.y);
					if (!boughtSkin3) fontLittle.draw(batch, "6 diamond", buttonSkin3.x+5, buttonSkin3.y+260);
					else fontLittle.draw(batch, "", buttonSkin3.x+5, buttonSkin3.y+260);
					if (!boughtSkin4) fontLittle.draw(batch, "6 diamond", buttonSkin4.x+5, buttonSkin4.y+260);
					else fontLittle.draw(batch, "", buttonSkin4.x+5, buttonSkin4.y+260);
					if (!boughtSkin5) fontLittle.draw(batch, "6 diamond", buttonSkin5.x+5, buttonSkin5.y+260);
					else fontLittle.draw(batch, "", buttonSkin5.x+5, buttonSkin5.y+260);
				}
				batch.draw(imgBackListFight, buttonBackListFight.x, buttonBackListFight.y);
				batch.draw(imgListLeft, buttonListLeftSkins.x, buttonListLeftSkins.y);
				batch.draw(imgListRight, buttonListRightSkins.x, buttonListRightSkins.y);
				font.draw(batch, "Skins", 570, 530);

			}
		} else if (numImg == 2){
			game = true;
			batch.draw(imgFonL, 0, 0);
			batch.draw(imgButtonBack, buttonBack.x, buttonBack.y);
			for (int i = monster.size - 1; i >= 0; i--) monster.get(i).move();
			if (timeLastSpawnEnemy + timeIntervalSpawnEnemy < TimeUtils.millis()) {
				timeLastSpawnEnemy = TimeUtils.millis();
				timeIntervalSpawnEnemy = MathUtils.random(10000, 15000);
				monster.add(new Monster(SCR_WIDTH, SCR_HEIGHT/2-45, 330, 330, 20, 10, 80));
				for (int i = monster.size - 1; i >= 0; i--)
				if (monster.get(i).numMonsters == 0) monster.get(i).timeFasaInterval = 100;
				else if (monster.get(i).numMonsters == 1) monster.get(i).timeFasaInterval = 80;
				else if (monster.get(i).numMonsters == 2) wallBreaker.get(i).timeFasaInterval = 50;
			}
			for (int i=0; i<monster.size; i++) {
				if (monster.get(i).x <= 300) {
					monster.get(i).x = 300;
					if (turel.isAlive && monster.get(i).state == ATTACK && monster.get(i).faza == 5) {
						monster.get(i).faza = 6;
						turel.life -= 40;
						if (turel.life <= 0) {
							turel.isAlive = false;
							turel.state = DEATHTUREL;
						}
					}
				}
				batch.draw(imgMonster[monster.get(i).numMonsters][monster.get(i).state][monster.get(i).faza], monster.get(i).x, monster.get(i).y, monster.get(i).width, monster.get(i).height);
				if (monster.get(i).life > 0)
					fontLittle.draw(batch, monster.get(i).life + " life", monster.get(i).x + monster.get(i).width / 2 - 20, monster.get(i).y + 300);
			}
			if (fazaTurelShoot) {
				for (int i = bullet.size - 1; i >= 0; i--) bullet.get(i).move();
				for (int i=0; i<bullet.size; i++){
					if (bullet.get(i).isAlive) {
						batch.draw(imgBullet, bullet.get(i).x - bullet.get(i).width / 2,
								bullet.get(i).y - bullet.get(i).height / 2,
								bullet.get(i).width, bullet.get(i).height);
					}
				}
			}
			turel.move();
			batch.draw(imgTurel[0][turel.state][turel.faza], turel.x, turel.y, turel.width, turel.height);
			if (turel.life > 0) fontLittle.draw(batch, turel.life+" life", turel.x+turel.width/2-20, turel.y+340);
			batch.draw(imgTablo, SCR_WIDTH/2-571/2, SCR_HEIGHT-86);
			if (numBullet == 1) batch.draw(imgBookATK, SCR_WIDTH-100, SCR_HEIGHT - 100);
			else if (numBullet == 2) batch.draw(imgBookATK2, SCR_WIDTH-100, SCR_HEIGHT - 100);
			else if (numBullet == 3) batch.draw(imgBookATK4, SCR_WIDTH-100, SCR_HEIGHT - 100);
			if (numNoteNeed1 == 1) fontLittle.draw(batch, "C", 450, SCR_HEIGHT-20);
			else if (numNoteNeed1 == 2) fontLittle.draw(batch, "Db", 450, SCR_HEIGHT-20);
			else if (numNoteNeed1 == 3) fontLittle.draw(batch, "D", 450, SCR_HEIGHT-20);
			else if (numNoteNeed1 == 4) fontLittle.draw(batch, "Eb", 450, SCR_HEIGHT-20);
			else if (numNoteNeed1 == 5) fontLittle.draw(batch, "E", 450, SCR_HEIGHT-20);
			else if (numNoteNeed1 == 6) fontLittle.draw(batch, "F", 450, SCR_HEIGHT-20);
			else if (numNoteNeed1 == 7) fontLittle.draw(batch, "Gb", 450, SCR_HEIGHT-20);
			else if (numNoteNeed1 == 8) fontLittle.draw(batch, "G", 450, SCR_HEIGHT-20);
			else if (numNoteNeed1 == 9) fontLittle.draw(batch, "Ab", 450, SCR_HEIGHT-20);
			else if (numNoteNeed1 == 10) fontLittle.draw(batch, "A", 450, SCR_HEIGHT-20);
			else if (numNoteNeed1 == 11) fontLittle.draw(batch, "Bb", 450, SCR_HEIGHT-20);
			else if (numNoteNeed1 == 12) fontLittle.draw(batch, "B", 450, SCR_HEIGHT-20);
			else if (numNoteNeed1 == 13) fontLittle.draw(batch, "C2", 450, SCR_HEIGHT-20);
			else if (numNoteNeed1 == 14) fontLittle.draw(batch, "Db2", 450, SCR_HEIGHT-20);
			if (numNoteNeed2 == 1) fontLittle.draw(batch, "C", 560, SCR_HEIGHT-20);
			else if (numNoteNeed2 == 2) fontLittle.draw(batch, "Db", 560, SCR_HEIGHT-20);
			else if (numNoteNeed2 == 3) fontLittle.draw(batch, "D", 560, SCR_HEIGHT-20);
			else if (numNoteNeed2 == 4) fontLittle.draw(batch, "Eb", 560, SCR_HEIGHT-20);
			else if (numNoteNeed2 == 5) fontLittle.draw(batch, "E", 560, SCR_HEIGHT-20);
			else if (numNoteNeed2 == 6) fontLittle.draw(batch, "F", 560, SCR_HEIGHT-20);
			else if (numNoteNeed2 == 7) fontLittle.draw(batch, "Gb", 560, SCR_HEIGHT-20);
			else if (numNoteNeed2 == 8) fontLittle.draw(batch, "G", 560, SCR_HEIGHT-20);
			else if (numNoteNeed2 == 9) fontLittle.draw(batch, "Ab", 560, SCR_HEIGHT-20);
			else if (numNoteNeed2 == 10) fontLittle.draw(batch, "A", 560, SCR_HEIGHT-20);
			else if (numNoteNeed2 == 11) fontLittle.draw(batch, "Bb", 560, SCR_HEIGHT-20);
			else if (numNoteNeed2 == 12) fontLittle.draw(batch, "B", 560, SCR_HEIGHT-20);
			else if (numNoteNeed2 == 13) fontLittle.draw(batch, "C2", 560, SCR_HEIGHT-20);
			else if (numNoteNeed2 == 14) fontLittle.draw(batch, "Db2", 560, SCR_HEIGHT-20);
			if (numNoteNeed3 == 1) fontLittle.draw(batch, "C", 660, SCR_HEIGHT-20);
			else if (numNoteNeed3 == 2) fontLittle.draw(batch, "Db", 660, SCR_HEIGHT-20);
			else if (numNoteNeed3 == 3) fontLittle.draw(batch, "D", 660, SCR_HEIGHT-20);
			else if (numNoteNeed3 == 4) fontLittle.draw(batch, "Eb", 660, SCR_HEIGHT-20);
			else if (numNoteNeed3 == 5) fontLittle.draw(batch, "E", 660, SCR_HEIGHT-20);
			else if (numNoteNeed3 == 6) fontLittle.draw(batch, "F", 660, SCR_HEIGHT-20);
			else if (numNoteNeed3 == 7) fontLittle.draw(batch, "Gb", 660, SCR_HEIGHT-20);
			else if (numNoteNeed3 == 8) fontLittle.draw(batch, "G", 660, SCR_HEIGHT-20);
			else if (numNoteNeed3 == 9) fontLittle.draw(batch, "Ab", 660, SCR_HEIGHT-20);
			else if (numNoteNeed3 == 10) fontLittle.draw(batch, "A", 660, SCR_HEIGHT-20);
			else if (numNoteNeed3 == 11) fontLittle.draw(batch, "Bb", 660, SCR_HEIGHT-20);
			else if (numNoteNeed3 == 12) fontLittle.draw(batch, "B", 660, SCR_HEIGHT-20);
			else if (numNoteNeed3 == 13) fontLittle.draw(batch, "C2", 660, SCR_HEIGHT-20);
			else if (numNoteNeed3 == 14) fontLittle.draw(batch, "Db2", 660, SCR_HEIGHT-20);
			if (numNoteNeed4 == 1) fontLittle.draw(batch, "C", 760, SCR_HEIGHT-20);
			else if (numNoteNeed4 == 2) fontLittle.draw(batch, "Db", 760, SCR_HEIGHT-20);
			else if (numNoteNeed4 == 3) fontLittle.draw(batch, "D", 760, SCR_HEIGHT-20);
			else if (numNoteNeed4 == 4) fontLittle.draw(batch, "Eb", 760, SCR_HEIGHT-20);
			else if (numNoteNeed4 == 5) fontLittle.draw(batch, "E", 760, SCR_HEIGHT-20);
			else if (numNoteNeed4 == 6) fontLittle.draw(batch, "F", 760, SCR_HEIGHT-20);
			else if (numNoteNeed4 == 7) fontLittle.draw(batch, "Gb", 760, SCR_HEIGHT-20);
			else if (numNoteNeed4 == 8) fontLittle.draw(batch, "G", 760, SCR_HEIGHT-20);
			else if (numNoteNeed4 == 9) fontLittle.draw(batch, "Ab", 760, SCR_HEIGHT-20);
			else if (numNoteNeed4 == 10) fontLittle.draw(batch, "A", 760, SCR_HEIGHT-20);
			else if (numNoteNeed4 == 11) fontLittle.draw(batch, "Bb", 760, SCR_HEIGHT-20);
			else if (numNoteNeed4 == 12) fontLittle.draw(batch, "B", 760, SCR_HEIGHT-20);
			else if (numNoteNeed4 == 13) fontLittle.draw(batch, "C2", 760, SCR_HEIGHT-20);
			else if (numNoteNeed4 == 14) fontLittle.draw(batch, "Db2", 760, SCR_HEIGHT-20);
			batch.draw(imgFonLR, 0, 0);
			batch.draw(imgFonLP, 0, 0);

			fontLittle.draw(batch, hpPlayer+" HP", 30, 350);
			fontLittle.draw(batch, level+" level", 30, 400);

		} else if (numImg == 3) {
			game = true;
			batch.draw(imgFonO, 0, 0);
			batch.draw(imgButtonBack0, buttonBack.x, buttonBack.y);
		}
		if (game) {
			if (isHitKeyC && !exit) batch.draw(imgWAKey, buttonC.x, buttonC.y); else batch.draw(imgWKey, buttonC.x, buttonC.y);
			if (isHitKeyD && !exit) batch.draw(imgWAKey, buttonD.x, buttonD.y); else batch.draw(imgWKey, buttonD.x, buttonD.y);
			if (isHitKeyE && !exit) batch.draw(imgWAKey, buttonE.x, buttonE.y); else batch.draw(imgWKey, buttonE.x, buttonE.y);
			if (isHitKeyF && !exit) batch.draw(imgWAKey, buttonF.x, buttonF.y); else batch.draw(imgWKey, buttonF.x, buttonF.y);
			if (isHitKeyG && !exit) batch.draw(imgWAKey, buttonG.x, buttonG.y); else batch.draw(imgWKey, buttonG.x, buttonG.y);
			if (isHitKeyA && !exit) batch.draw(imgWAKey, buttonA.x, buttonA.y); else batch.draw(imgWKey, buttonA.x, buttonA.y);
			if (isHitKeyB && !exit) batch.draw(imgWAKey, buttonB.x, buttonB.y); else batch.draw(imgWKey, buttonB.x, buttonB.y);
			if (isHitKeyC2 && !exit) batch.draw(imgWAKey, buttonC2.x, buttonC2.y); else batch.draw(imgWKey, buttonC2.x, buttonC2.y);
			if (isHitKeyDb && !exit) batch.draw(imgBAKey, buttonDb.x, buttonDb.y); else batch.draw(imgBKey, buttonDb.x, buttonDb.y);
			if (isHitKeyEb && !exit) batch.draw(imgBAKey, buttonEb.x, buttonEb.y); else batch.draw(imgBKey, buttonEb.x, buttonEb.y);
			if (isHitKeyGb && !exit) batch.draw(imgBAKey, buttonGb.x, buttonGb.y); else batch.draw(imgBKey, buttonGb.x, buttonGb.y);
			if (isHitKeyAb && !exit) batch.draw(imgBAKey, buttonAb.x, buttonAb.y); else batch.draw(imgBKey, buttonAb.x, buttonAb.y);
			if (isHitKeyBb && !exit) batch.draw(imgBAKey, buttonBb.x, buttonBb.y); else batch.draw(imgBKey, buttonBb.x, buttonBb.y);
			if (isHitKeyDb2 && !exit) batch.draw(imgBAKeyP, buttonDb2.x, buttonDb2.y); else batch.draw(imgBKeyP, buttonDb2.x, buttonDb2.y);
		}
		if (numImg == 3) {
			fontRussian.draw(batch, " до         ре          ми        фа        соль        ля          си        до2", 60, 100);
			fontRussian.draw(batch, " C           D           E          F          G           A           B         C2", 60, 60);
			fontRussian.draw(batch, " ре бимоль   ми бимоль       соль бимоль ля бимоль си бимоль    ре бимоль2 ", 60, 360);
			fontRussian.draw(batch, "      Db          Eb                     Gb         Ab          Bb                 Db2 ", 60, 400);
		}
		if (exit) batch.draw(imgExit, 448, 115);
		if (Final) {
			batch.draw(imgFonLF, 0, 0);
			fontBig.draw(batch, "Thanks for completing", 80, 500);
			fontBig.draw(batch, "my game!", 80, 400);
			font.draw(batch, "to be continued...", 50, 50);
		}
		batch.end();
	}
	void defineObjects(){
		if (numTool == 1) nameNote = "";
		else if (numTool == 2) nameNote = "К";
		else if (numTool == 3) nameNote = "Ш";
		else if (numTool == 4) nameNote = "С";
		else if (numTool == 5) nameNote = "П";
		else if (numTool == 6) nameNote = "Р";

		sndC = Gdx.audio.newSound(Gdx.files.internal("до"+nameNote+".mp3"));
		sndDb = Gdx.audio.newSound(Gdx.files.internal("до#"+nameNote+".mp3"));
		sndD = Gdx.audio.newSound(Gdx.files.internal("ре"+nameNote+".mp3"));
		sndEb = Gdx.audio.newSound(Gdx.files.internal("ре#"+nameNote+".mp3"));
		sndE = Gdx.audio.newSound(Gdx.files.internal("ми"+nameNote+".mp3"));
		sndF = Gdx.audio.newSound(Gdx.files.internal("фа"+nameNote+".mp3"));
		sndGb = Gdx.audio.newSound(Gdx.files.internal("фа#"+nameNote+".mp3"));
		sndG = Gdx.audio.newSound(Gdx.files.internal("соль"+nameNote+".mp3"));
		sndAb = Gdx.audio.newSound(Gdx.files.internal("соль#"+nameNote+".mp3"));
		sndA = Gdx.audio.newSound(Gdx.files.internal("ля"+nameNote+".mp3"));
		sndBb = Gdx.audio.newSound(Gdx.files.internal("ля#"+nameNote+".mp3"));
		sndB = Gdx.audio.newSound(Gdx.files.internal("си"+nameNote+".mp3"));
		sndC2 = Gdx.audio.newSound(Gdx.files.internal("до2"+nameNote+".mp3"));
		sndDb2 = Gdx.audio.newSound(Gdx.files.internal("до2#"+nameNote+".mp3"));

		imgFonL = new Texture("fonL"+location+".png");
		imgFonLP = new Texture("фонЛокация"+location+"П.png");
		imgFonLR = new Texture("fonL"+location+"R.png");

		imgBKey = new Texture(numSkin+"keyB.png");
		imgBAKey = new Texture(numSkin+"keyBA.png");
		imgWKey = new Texture(numSkin+"keyW.png");
		imgWAKey = new Texture(numSkin+"keyWA.png");
		imgBKeyP = new Texture(numSkin+"keyBH.png");
		imgBAKeyP = new Texture(numSkin+"keyBHA.png");

		imgBoss = new Texture("boss"+location+"L.png");
	}

	void defineToolInfa(){
		imgToolIcon = new Texture(numListTool+"toolIcon.png");
		if (numListTool == 1) {
			hpPlayerInfoTool = 100;
			atkPlayerInfoTool = 10;
		} else if (numListTool == 2) {
			hpPlayerInfoTool = 200;
			atkPlayerInfoTool = 30;
		} else if (numListTool == 3) {
			hpPlayerInfoTool = 400;
			atkPlayerInfoTool = 50;
		} else if (numListTool == 4) {
			hpPlayerInfoTool = 800;
			atkPlayerInfoTool = 60;
		} else if (numListTool == 5) {
			hpPlayerInfoTool = 1000;
			atkPlayerInfoTool = 80;
		} else if (numListTool == 6) {
			hpPlayerInfoTool = 1400;
			atkPlayerInfoTool = 100;
		} else if (numListTool == 7) {
			hpPlayerInfoTool = 1800;
			atkPlayerInfoTool = 150;
		} else if (numListTool == 8) {
			hpPlayerInfoTool = 2500;
			atkPlayerInfoTool = 300;
		} else if (numListTool == 9) {
			hpPlayerInfoTool = 4000;
			atkPlayerInfoTool = 500;
		} else if (numListTool == 10) {
			hpPlayerInfoTool = 5000;
			atkPlayerInfoTool = 800;
		}
	}
	void defineTool(){
		if (numTool == 1) {
			hpPlayer = 100;
			atkPlayer = 10;
		} else if (numTool == 2) {
			hpPlayer = 200;
			atkPlayer = 30;
		} else if (numTool == 3) {
			hpPlayer = 400;
			atkPlayer = 50;
		} else if (numTool == 4) {
			hpPlayer = 800;
			atkPlayer = 60;
		} else if (numTool == 5) {
			hpPlayer = 1000;
			atkPlayer = 80;
		} else if (numTool == 6) {
			hpPlayer = 1400;
			atkPlayer = 100;
		} else if (numTool == 7) {
			hpPlayer = 1800;
			atkPlayer = 150;
		} else if (numTool == 8) {
			hpPlayer = 2500;
			atkPlayer = 300;
		} else if (numTool == 9) {
			hpPlayer = 4000;
			atkPlayer = 500;
		} else if (numTool == 10) {
			hpPlayer = 5000;
			atkPlayer = 800;
		}
	}
	@Override
	public void dispose () {
		batch.dispose();
		imgPlay.dispose();
	}
}