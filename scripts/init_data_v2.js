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


var pluton2 = affiliationRepository.save(
    Affiliation.builder()
        .name("2 pluton")
        .build()
);

var kowalskiAuthor =
    Author.builder()
        .name("Jan")
        .surname("Kowalski")
        .affiliation(pluton2)
        .build();


kowalskiAuthor.setRank("sierż. pchor.")
authorRepository.save(kowalskiAuthor);
