var Affiliation = Java.type('pl.edu.wat.knowledge.entity.Affiliation');
var Article = Java.type('pl.edu.wat.knowledge.entity.Article');
var Author = Java.type('pl.edu.wat.knowledge.entity.Author');
var Book = Java.type('pl.edu.wat.knowledge.entity.Book');
var Chapter = Java.type('pl.edu.wat.knowledge.entity.Chapter');
var Journal = Java.type('pl.edu.wat.knowledge.entity.Journal');
var Publisher = Java.type('pl.edu.wat.knowledge.entity.Publisher');
var Issn = Java.type('pl.edu.wat.knowledge.entity.Journal.Issn');
var Isbn = Java.type('pl.edu.wat.knowledge.entity.Book.Isbn');
var Set = Java.type('java.util.Set');


var watPublisher = publisherRepository.save(
    Publisher.builder()
        .location("Warszawa")
        .name("Wojskowa Akademia Techniczna")
        .build()
);
var pwnPublisher = publisherRepository.save(
    Publisher.builder()
        .location("Warszawa")
        .name("Wydawnictwo Naukowe PWN")
        .build()
);

var ieeeJournal = journalRepository.save(
    Journal.builder()
        .publisher(pwnPublisher)
        .title("IEEE Transactions on Emerging Topics in Computing")
        .issn(new Issn("2168-6750"))
        .baseScore(140)
        .build()
);

var watJournal = journalRepository.save(
    Journal.builder()
        .publisher(watPublisher)
        .title("Biuletyn Wojskowej Akademii Technicznej ")
        .issn(new Issn("1234-5865"))
        .baseScore(5)
        .build()
);

var watAffiliation = affiliationRepository.save(
    Affiliation.builder()
        .name("Wojskowa Akademia Techniczna")
        .build()
);
var wcyAffiliation = affiliationRepository.save(
    Affiliation.builder()
        .name("Wydział Cybernetyki")
        .parent(watAffiliation)
        .build()
)
var wloAffiliation = affiliationRepository.save(
    Affiliation.builder()
        .name("Wydział Bezpieczeństwa, Logistyki i Zarządzania")
        .parent(watAffiliation)
        .build()
);

var marcinAuthor = authorRepository.save(
    Author.builder()
        .name("Marcin")
        .surname("Krukowski")
        .affiliation(wcyAffiliation)
        .build()
);

var piotrAuthor = authorRepository.save(
    Author.builder()
        .name("Piotr")
        .surname("Jawowski")
        .affiliation(wcyAffiliation)
        .build()
);

var patrycjaAuthor = authorRepository.save(
    Author.builder()
        .name("Patrycja")
        .surname("Woda")
        .affiliation(wloAffiliation)
        .build()
);

var bookCrypto = bookRepository.save(
    Book.builder()
        .title("CyberExpert 2021 - Metody i narzędzia w procesie tworzenia cyberzdolności Sił Zbrojnych RP - wyzwania i perspektywy")
        .publisher(watPublisher)
        .year(2022)
        .isbn(new Isbn("978-83-7938-366-5"))
        .baseScore(80)
        .build()
);

var marcinChapter = chapterRepository.save(
    Chapter.builder()
        .title("Wymagania dotyczące wartości tłumienności wtrąceniowej filtrów zasilania z punktu widzenia bezpieczeństwa elektromagnetycznego urządzeń IT")
        .book(bookCrypto)
        .authors(Set.of(marcinAuthor))
        .collection("93-104")
        .score(40)
        .build()
);


var piotrChapter = chapterRepository.save(
    Chapter.builder()
        .title("Modelowanie i analiza procesu złośliwego sterowania ludźmi")
        .book(bookCrypto)
        .authors(Set.of(piotrAuthor))
        .collection("0-56")
        .score(40)
        .build()
);

var patrycjaArticle = articleRepository.save(
    Article.builder()
        .title("Wsparcie systemu niemilitarnego państwa przez Wojska Obrony Terytorialnej w walce z pandemią Covid-19")
        .journal(watJournal)
        .authors(Set.of(patrycjaAuthor))
        .collection("4-5")
        .vol(3)
        .no(2)
        .score(5)
        .build()
);

