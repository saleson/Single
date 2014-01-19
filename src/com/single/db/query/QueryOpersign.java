package com.single.db.query;

public enum QueryOpersign {

	NONE, //         = -1,
    EQUAL, //        =  0,	=
    NOTEQUAL, //     =  1,	!=
    LESSTHAN, //     =  2,	<
    MOREOREQUAL, //  =  3,	>=
    GREATERTHAN, //  =  4,	>
    LESSOREQUAL, //  =  5,	<=
    LIKE, //         =  6,	like
    NOTLIKE, //      =  7,	not like
    NULL, //         =  8,	is null
    NOTNULL, //      =  9,	is not null
    BETWEEN, //      = 10,	between {1} and {2}
    NOTBETWEEN, //   = 11,	not between {1} and {2}
    IN, //           = 12,	in 
    NOTIN; //        = 13,	not in
}
