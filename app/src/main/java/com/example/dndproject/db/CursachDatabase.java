package com.example.dndproject.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Transaction;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.dndproject.db.dao.*;
import com.example.dndproject.db.dao.riches.ArtworksDao;
import com.example.dndproject.db.dao.riches.ArtworksRichesMMDao;
import com.example.dndproject.db.dao.riches.CoinsDao;
import com.example.dndproject.db.dao.riches.CoinsRichesMMDao;
import com.example.dndproject.db.dao.riches.Precious_stonesDao;
import com.example.dndproject.db.dao.riches.Precious_stonesRichesMMDao;
import com.example.dndproject.db.dao.riches.RichesDao;
import com.example.dndproject.db.dao.riches.TrinketsDao;
import com.example.dndproject.db.dao.riches.TrinketsRichesMMDao;
import com.example.dndproject.db.entities.*;
import com.example.dndproject.db.entities.riches.Artworks;
import com.example.dndproject.db.entities.riches.ArtworksRichesMM;
import com.example.dndproject.db.entities.riches.Coins;
import com.example.dndproject.db.entities.riches.CoinsRichesMM;
import com.example.dndproject.db.entities.riches.Precious_stones;
import com.example.dndproject.db.entities.riches.Precious_stonesRichesMM;
import com.example.dndproject.db.entities.riches.Riches;
import com.example.dndproject.db.entities.riches.Trinkets;
import com.example.dndproject.db.entities.riches.TrinketsRichesMM;

@Database(entities = {Artworks.class, Class.class,
        Coins.class, Precious_stones.class,
        Riches.class, Spell.class,
        Trinkets.class, CoinsRichesMM.class, TrinketsRichesMM.class,
        ArtworksRichesMM.class, Precious_stonesRichesMM.class}, version = 1)
public abstract class CursachDatabase extends RoomDatabase {

    public abstract ArtworksDao artworksDao();

    public abstract CoinsDao coinsDao();

    public abstract Precious_stonesDao precious_stonesDao();

    public abstract RichesDao richesDao();

    public abstract SpellDao spellDao();

    public abstract TrinketsDao trinketsDao();

    public abstract ArtworksRichesMMDao artworksRichesMMDao();

    public abstract Precious_stonesRichesMMDao precious_stonesRichesMMDao();

    public abstract CoinsRichesMMDao coinsRichesMMDao();

    public abstract TrinketsRichesMMDao trinketsRichesMMDao();

    private static volatile CursachDatabase INSTANCE;

    public static CursachDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (CursachDatabase.class) {
                if (INSTANCE == null) {
                    RoomDatabase.Callback rdc = new RoomDatabase.Callback() {
                        public void onCreate(SupportSQLiteDatabase db) {
                            insertStartData(db);
                        }

                        public void onOpen(SupportSQLiteDatabase db) {

                        }
                    };

                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(),
                                    CursachDatabase.class, "cursach_db_2")
                            .addCallback(rdc)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    @Transaction
    private static void insertStartData(SupportSQLiteDatabase db) {
        Thread thread = new Thread(() -> {
            //Riches
            db.execSQL("INSERT INTO Coins (currencie, hazard, bone_number, coins_amount) VALUES " +
                    "('Медь', '0–4', '{1,30}', '{5,1}'), " +
                    "('Серебро', '0–4', '{31,60}', '{4,1}'), " +
                    "('Электрум', '0–4', '{61,70}', '{3,1}'), " +
                    "('Золото', '0–4', '{71,95}', '{3,1}'), " +
                    "('Платина', '0–4', '{96,100}', '{1,1}'), " +
                    "('Медь', '5–10', '{1,30}', '{4,100}'), " +
                    "('Серебро', '5–10', '{31,60}', '{6,10}'), " +
                    "('Электрум', '5–10', '{1,30}', '{1,10}'), " +
                    "('Электрум', '5–10', '{61,70}', '{3,10}'), " +
                    "('Золото', '5–10', '{31,60}', '{2,10}'), " +
                    "('Золото', '5–10', '{61,70}', '{2,10}'), " +
                    "('Золото', '5–10', '{71,95}', '{4,10}'), " +
                    "('Золото', '5–10', '{96,100}', '{2,10}'), " +
                    "('Платина', '5–10', '{96,100}', '{3,1}'), " +
                    "('Серебро', '11–16', '{1,20}', '{4,100}'), " +
                    "('Электрум', '11–16', '{21,35}', '{1,100}'), " +
                    "('Золото', '11–16', '{1,20}', '{1,100}'), " +
                    "('Золото', '11–16', '{21,35}', '{1,100}'), " +
                    "('Золото', '11–16', '{36,75}', '{2,100}'), " +
                    "('Золото', '11–16', '{76,100}', '{2,100}'), " +
                    "('Платина', '11–16', '{36,75}', '{1,10}'), " +
                    "('Платина', '11–16', '{76,100}', '{2,10}'), " +
                    "('Электрум', '17+', '{1,15}', '{2,1000}'), " +
                    "('Золото', '17+', '{1,15}', '{8,100}'), " +
                    "('Золото', '17+', '{16,55}', '{1,1000}'), " +
                    "('Золото', '17+', '{56,100}', '{1,1000}'), " +
                    "('Платина', '17+', '{16,55}', '{1,100}'), " +
                    "('Платина', '17+', '{56,100}', '{2,100}');");

            db.execSQL("INSERT INTO Precious_stones (bone_number, stone_name, stone_description, stone_price) VALUES " +
                    "(1, 'Азурит', 'непрозрачный и тёмно-синий', 10), " +
                    "(2, 'Бирюза', 'непрозрачная и зелёно-голубая', 10), " +
                    "(3, 'Гематит', 'непрозрачный, серо-чёрный', 10), " +
                    "(4, 'Глазчатый агат', 'полупрозрачный, чередующиеся круги серого, белого, коричневого, голубого или зелёного цвета', 10), " +
                    "(5, 'Голубой кварц', 'прозрачный, голубой', 10), " +
                    "(6, 'Лазурит', 'непрозрачный, голубой и синий с жёлтыми вкраплениями', 10), " +
                    "(7, 'Малахит', 'непрозрачный, с чередующимися светло- и тёмно-зелёными полосами', 10), " +
                    "(8, 'Моховой агат', 'полупрозрачный, розовый или бело-жёлтый с мшистыми серыми или зелёными пятнами', 10), " +
                    "(9, 'Обсидиан', 'непрозрачный, чёрный', 10), " +
                    "(10, 'Полосчатый агат', 'полупрозрачный, с чередующимися коричневыми, голубыми, белыми или красными полосами', 10), " +
                    "(11, 'Родохрозит', 'непрозрачный, светло-розовый', 10), " +
                    "(12, 'Тигровый глаз', 'полупрозрачный, коричневый с золотой серединкой', 10), " +
                    "(1, 'Гелиотроп', 'непрозрачный, тёмно-серый с красными вкраплениями', 50), " +
                    "(2, 'Звёздчатый розовый кварц', 'полупрозрачный, розовый камень с белой звездой по центру', 50), " +
                    "(3, 'Кварц', 'прозрачный, белый, дымчатый или жёлтый', 50), " +
                    "(4, 'Лунный камень', 'полупрозрачный, белый с бледно-голубым отливом', 50), " +
                    "(5, 'Оникс', 'непрозрачный, чёрно-белые полосы или чисто чёрный или белый', 50), " +
                    "(6, 'Сардоникс', 'непрозрачный, бело-красные полосы', 50), " +
                    "(7, 'Сердолик', 'непрозрачный, оранжевый с переходом в красно-коричневый', 50), " +
                    "(8, 'Халцедон', 'непрозрачный, белый', 50), " +
                    "(9, 'Хризопраз', 'полупрозрачный, зелёный', 50), " +
                    "(10, 'Циркон', 'прозрачный, бледный зелёно-голубой', 50), " +
                    "(11, 'Цитрин', 'прозрачный, жёлто-коричневый', 50), " +
                    "(12, 'Яшма', 'непрозрачная, синяя, чёрная или коричневая', 50), " +
                    "(1, 'Аметист', 'прозрачный, тёмно-фиолетовый', 100), " +
                    "(2, 'Гагат', 'непрозрачный, чёрный', 100), " +
                    "(3, 'Гранат', 'прозрачный, красный, зелёно-коричневый или фиолетовый', 100), " +
                    "(4, 'Жемчуг ', 'непрозрачный, переливчатый белый, жёлтый или розовый', 100), " +
                    "(5, 'Коралл', 'непрозрачный, тёмно-красный', 100), " +
                    "(6, 'Нефрит', 'полупрозрачный, светло- или тёмно- зелёный, белый', 100), " +
                    "(7, 'Турмалин', 'прозрачный, бледно-зелёный, синий, коричневый или красный', 100), " +
                    "(8, 'Хризоберилл', 'прозрачный, жёлто-зелёный или светло-зелёный', 100), " +
                    "(9, 'Шпинель', 'прозрачная, красная, красно- коричневая или тёмно-зелёная', 100), " +
                    "(10, 'Янтарь', 'прозрачный, жёлто-золотой', 100), " +
                    "(1, 'Аквамарин', 'прозрачный, зелёно-голубой', 500), " +
                    "(2, 'Александрит', 'прозрачный, тёмно-зелёный', 500), " +
                    "(3, 'Синяя шпинель', 'прозрачная, синяя', 500), " +
                    "(4, 'Топаз', 'прозрачный, золотисто-жёлтый', 500), " +
                    "(5, 'Хризолит', 'прозрачный, сочный оливково-зелёный', 500), " +
                    "(6, 'Чёрный жемчуг', 'непрозрачный, чисто чёрный', 500), " +
                    "(1, 'Голубой сапфир', 'прозрачный, от бело-голубого до умеренно синего', 1000), " +
                    "(2, 'Жёлтый сапфир', 'прозрачный, огненно-жёлтый или жёлто-зелёный', 1000), " +
                    "(3, 'Звёздчатый рубин', 'полупрозрачный, рубин с белёсой звездой в центре', 1000), " +
                    "(4, 'Звёздчатый сапфир', 'полупрозрачный, синий сапфир с белёсой звездой в центре', 1000), " +
                    "(5, 'Изумруд', 'прозрачный, насыщенный ярко- зелёный', 1000), " +
                    "(6, 'Огненный опал', 'полупрозрачный, огненно-красный', 1000), " +
                    "(7, 'Опал', 'полупрозрачный, голубой с зелёными и золотыми вкраплениями', 1000), " +
                    "(8, 'Чёрный опал', 'полупрозрачный, тёмно-зелёный с чёрными пятнышками и золотыми вкраплениями', 1000), " +
                    "(1, 'Алмаз', 'прозрачный, сине-белый, ярко-жёлтый, розовый, коричневый или синий', 5000), " +
                    "(2, 'Гиацинт', 'прозрачный, огненно-оранжевый', 5000), " +
                    "(3, 'Рубин', 'прозрачный, чисто красный с переходом в тёмно-алый', 5000), " +
                    "(4, 'Чёрный сапфир', 'полупрозрачный, блестящий чёрный с яркими вкраплениями', 5000);");

            db.execSQL("INSERT INTO Trinkets (bone_number, trinket_name) VALUES " +
                    "(1, 'Мумифицированная рука гоблина'), " +
                    "(2, 'Кусочек кристалла, слабо светящийся в лунном свете'), " +
                    "(3, 'Золотая монета, отчеканенная в неизвестной стране'), " +
                    "(4, 'Дневник, написанный на неизвестном вам языке'), " +
                    "(5, 'Латунное кольцо, которое не темнеет со временем'), " +
                    "(6, 'Старая стеклянная шахматная фигура'), " +
                    "(7, 'Пара игральных костей, у обеих вместо шестёрок нарисованы черепа'), " +
                    "(8, 'Небольшой идол, изображающий страшное чудовище, насылающий кошмары, когда вы спите рядом с ним'), " +
                    "(9, 'Верёвочное ожерелье, на котором висят четыре мумифицированных эльфийских пальца'), " +
                    "(10, 'Квитанция для получения посылки в неизвестном вам королевстве'), " +
                    "(11, '30-граммовый кусочек неизвестного материала'), " +
                    "(12, 'Маленькая тряпичная кукла, утыканная иголками'), " +
                    "(13, 'Зуб неизвестного зверя'), " +
                    "(14, 'Огромная чешуйка, возможно, драконья'), " +
                    "(15, 'Ярко-зелёное перо'), " +
                    "(16, 'Старая гадальная карта с лицом, похожим на ваше'), " +
                    "(17, 'Стеклянная сфера, заполненная дымом'), " +
                    "(18, 'Полукилограммовое яйцо с ярко-красной скорлупой'), " +
                    "(19, 'Курительная трубка, из которой вылетают пузыри'), " +
                    "(20, 'Стеклянный графин с мутной жидкостью, в которой плавает странный кусочек мяса'), " +
                    "(21, 'Крошечная музыкальная шкатулка гномьей работы, наигрывающая мелодию, напоминающую вам о детстве'), " +
                    "(22, 'Маленькая деревянная статуэтка нарядного полурослика'), " +
                    "(23, 'Латунная сфера, покрытая странными рунами'), " +
                    "(24, 'Разноцветный каменный диск'), " +
                    "(25, 'Крошечный серебряный ворон'), " +
                    "(26, 'Сумка, в которой лежат 47 человеческих зубов. Один с кариесом'), " +
                    "(27, 'Кусок обсидиана, всегда тёплый на ощупь'), " +
                    "(28, 'Коготь дракона, подвешенный на простом кожаном шнурке'), " +
                    "(29, 'Пара старых носков'), " +
                    "(30, 'Чистая книга, на страницах которой не получается писать ни чернилами, ни мелом, ни углём, и никакими другими обычными средствами'), " +
                    "(31, 'Серебряный значок в форме пятиконечной звезды'), " +
                    "(32, 'Нож, принадлежавший родственнику'), " +
                    "(33, 'Стеклянный флакон с обрезками ногтей'), " +
                    "(34, 'Прямоугольное металлическое устройство с двумя крошечными металлическими колпачками на одной стороне. Если его намочить, оно сыпет искры'), " +
                    "(35, 'Белые перчатки с блёстками'), " +
                    "(36, 'Одеяние с сотней крохотных карманов'), " +
                    "(37, 'Маленький каменный кирпич, который ничего не весит'), " +
                    "(38, 'Крохотный набросок портрета гоблина'), " +
                    "(39, 'Пустой флакон, пахнущий духами при открытии'), " +
                    "(40, 'Драгоценный камень, который всем кроме вас кажется куском угля'), " +
                    "(41, 'Кусок старого знамени'), " +
                    "(42, 'Знак различия древнего легиона'), " +
                    "(43, 'Крохотный серебряный колокольчик без язычка'), " +
                    "(44, 'Механическая канарейка в гномьей лампе'), " +
                    "(45, 'Крохотный сундук, вырезанный так, что кажется, что у него невероятно глубокое дно'), " +
                    "(46, 'Мёртвый спрайт в чистой стеклянной бутылке'), " +
                    "(47, 'Запаянная металлическая банка, в которой, судя по звукам, находится жидкость, песок, пауки или битое стекло'), " +
                    "(48, 'Стеклянная сфера с водой, в которой плавает механическая золотая рыбка'), " +
                    "(49, 'Серебряная ложка с выгравированной на ручке буквой «М»'), " +
                    "(50, 'Свисток из дерева золотого цвета'), " +
                    "(51, 'Мёртвый скарабей размером с ладонь'), " +
                    "(52, 'Два игрушечных солдатика, один без головы'), " +
                    "(53, 'Небольшая коробка с пуговицами разного размера'), " +
                    "(54, 'Свеча, которая никак не загорается'), " +
                    "(55, 'Крохотная клетка без дверцы'), " +
                    "(56, 'Старый ключ'), " +
                    "(57, 'Не поддающаяся расшифровке карта сокровищ'), " +
                    "(58, 'Рукоятка от сломанного меча'), " +
                    "(59, 'Кроличья лапка'), " +
                    "(60, 'Стеклянный глаз'), " +
                    "(61, 'Камея с резным портретом ужасного лица'), " +
                    "(62, 'Серебряный череп размером с монету'), " +
                    "(63, 'Алебастровая маска'), " +
                    "(64, 'Пирамидка клейкого чёрного дурно пахнущего благовония'), " +
                    "(65, 'Ночной колпак, дарующий приятные сны'), " +
                    "(66, 'Один костяной калтроп'), " +
                    "(67, 'Золотая оправа монокля без линзы'), " +
                    "(68, 'Кубик с длиной ребра 2 сантиметра, все стороны раскрашены в разные цвета'), " +
                    "(69, 'Хрустальная дверная ручка'), " +
                    "(70, 'Пакетик розовой пыли'), " +
                    "(71, 'Два листа пергамента с фрагментом нот прекрасной песни'), " +
                    "(72, 'Серебряная серьга в виде слезинки, сделанная из настоящей слезинки'), " +
                    "(73, 'Яичная скорлупа, разрисованная с жуткими подробностями сценами человеческих мук'), " +
                    "(74, 'Веер, на котором в раскрытом состоянии видно спящую кошку'), " +
                    "(75, 'Набор костяных трубочек'), " +
                    "(76, 'Четырёхлистный клевер, засушенный в книге о манерах и этикете'), " +
                    "(77, 'Лист пергамента, на котором изображена сложная механическая конструкция'), " +
                    "(78, 'Разукрашенные ножны, под которые вы никак не можете найти подходящий клинок'), " +
                    "(79, 'Приглашение на вечеринку, на которой произошло убийство'), " +
                    "(80, 'Бронзовая пентаграмма с выгравированной в центре крысиной головой'), " +
                    "(81, 'Фиолетовый носовой платок с вышитым именем великого архимага'), " +
                    "(82, 'Половинка плана храма, замка или другого строения'), " +
                    "(83, 'Кусочек сложенной ткани, который, если развернуть, превращается в модную кепку'), " +
                    "(84, 'Квитанция о депозите в банке далёкого города'), " +
                    "(85, 'Дневник, в котором не хватает семь страниц'), " +
                    "(86, 'Пустая серебряная табакерка с надписью на поверхности «грёзы»'), " +
                    "(87, 'Железный святой символ неизвестного божества'), " +
                    "(88, 'Книга о восхождении и свержении легендарного героя, в которой нет последней главы'), " +
                    "(89, 'Сосуд с драконьей кровью'), " +
                    "(90, 'Древняя эльфийская стрела'), " +
                    "(91, 'Иголка, которая никогда не гнётся'), " +
                    "(92, 'Красивая дварфская брошь'), " +
                    "(93, 'Пустая бутылка из-под вина с небольшой наклейкой «Винокурня Винного Волшебника, Красное драконье, 331422-В»'), " +
                    "(94, 'Плитка мозаики с разноцветной глазированной поверхностью'), " +
                    "(95, 'Окаменевшая мышь'), " +
                    "(96, 'Чёрный пиратский флаг с черепом и костями дракона'), " +
                    "(97, 'Крохотный металлический краб или паук, который двигается, когда на него не смотрят'), " +
                    "(98, 'Стеклянная бутылка сала с этикеткой «Жир грифона»'), " +
                    "(99, 'Деревянная коробка с керамическим дном, в которой живёт червяк с двумя головами на каждом конце тела'), " +
                    "(100, 'Металлическая урна с прахом героя');");

            db.execSQL("INSERT INTO Artworks (bone_number, art_name, art_price) VALUES " +
                    "(1, 'Серебряный кувшин', 25), " +
                    "(2, 'Резная статуэтка из кости', 25), " +
                    "(3, 'Маленький золотой браслет', 25), " +
                    "(4, 'Одеяние из золотой парчи', 25), " +
                    "(5, 'Чёрная бархатная маска, вышитая серебряной нитью', 25), " +
                    "(6, 'Медная чаша с серебряной филигранью', 25), " +
                    "(7, 'Пара гравированных игральных костей', 25), " +
                    "(8, 'Небольшое зеркальце в расписной деревянной раме', 25), " +
                    "(9, 'Вышитый шёлковый носовой платок', 25), " +
                    "(10, 'Золотой медальон с портретом возлюбленной внутри', 25), " +
                    "(1, 'Золотое кольцо с гелиотропами', 250), " +
                    "(2, 'Резная статуэтка слоновой кости', 250), " +
                    "(3, 'Большой золотой браслет', 250), " +
                    "(4, 'Серебряное ожерелье с кулоном из поделочного камня', 250), " +
                    "(5, 'Бронзовая корона', 250), " +
                    "(6, 'Шёлковая мантия с золотой вышивкой', 250), " +
                    "(7, 'Большой мастерски вытканный гобелен', 250), " +
                    "(8, 'Латунная кружка, инкрустированная нефритом', 250), " +
                    "(9, 'Коробочка с бирюзовыми фигурками животных', 250), " +
                    "(10, 'Золотая клетка для птиц, инкрустированная электрумом', 250), " +
                    "(1, 'Серебряная чаша, декорированная лунным камнем', 750), " +
                    "(2, 'Длинный меч из посеребрённой стали', 750), " +
                    "(3, 'Резная арфа из экзотической древесины, с инкрустацией из слоновой кости', 750), " +
                    "(4, 'Небольшой золотой идол', 750), " +
                    "(5, 'Гребень в виде золотого дракона со вставленными в глаза гранатами', 750), " +
                    "(6, 'Бутыль, запечатанная пробкой с тиснением по золотой фольге и украшенная аметистами', 750), " +
                    "(7, 'Церемониальный кинжал из электрума с чёрной жемчужиной', 750), " +
                    "(8, 'Серебяно-золотая брошь', 750), " +
                    "(9, 'Обсидиановая статуэтка с золотой инкрустацией', 750), " +
                    "(10, 'Расписная боевая маска из золота', 750), " +
                    "(1, 'Прекрасная золотая цепь с огненным опалом', 2500), " +
                    "(2, 'Старинный шедевр живописи', 2500), " +
                    "(3, 'Расшитая шёлком и бархатом мантия, украшенная лунными камнями', 2500), " +
                    "(4, 'Платиновый браслет с сапфиром', 2500), " +
                    "(5, 'Вышитые перчатки, усыпанные драгоценными камнями', 2500), " +
                    "(6, 'Украшенный драгоценными камнями ножной браслет', 2500), " +
                    "(7, 'Золотая музыкальная шкатулка', 2500), " +
                    "(8, 'Золотой браслет, украшенный аквамаринами', 2500), " +
                    "(9, 'Глазная повязка с ложным глазом из голубого сапфира и лунного камня', 2500), " +
                    "(10, 'Ожерелье из маленьких розовых жемчужин', 2500), " +
                    "(1, 'Украшенная драгоценностями золотая корона', 7500), " +
                    "(2, 'Украшенная драгоценностями платиновое кольцо', 7500), " +
                    "(3, 'Небольшая золотая статуэтка с рубинами', 7500), " +
                    "(4, 'Золотой кубок с изумрудами', 7500), " +
                    "(5, 'Золотая шкатулка для драгоценностей с платиновой филигранью', 7500), " +
                    "(6, 'Расписанный золотом детский саркофаг', 7500), " +
                    "(7, 'Нефритовая игральная доска с золотыми фигурками', 7500), " +
                    "(8, 'Украшенный драгоценными камнями рог для напитков слоновой кости', 7500);");
        });
        thread.start();
    }
}
