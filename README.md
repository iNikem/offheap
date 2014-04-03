jpoint
======

This is demo project for JPoint conference, demostrating various means of using off-heap storage for data accumulation and manipulating.

On this author's MacBook Pro, end results are as follows:

Benchmark                                         Mode   Samples         Mean   Mean error    Units
e.p.j.StockExchangeBenchmark.arrayList            avgt         5     4309.324     5777.266    ms/op
e.p.j.StockExchangeBenchmark.byteBuffer           avgt         5     1778.355      220.249    ms/op
e.p.j.StockExchangeBenchmark.directByteBuffer     avgt         5      766.686      215.527    ms/op
e.p.j.StockExchangeBenchmark.linkedList           avgt         5    21554.219    26206.010    ms/op
e.p.j.StockExchangeBenchmark.mappedByteBuffer     avgt         5     1132.380     1201.578    ms/op
e.p.j.StockExchangeBenchmark.noop                 avgt         5       17.313        0.650    ms/op
e.p.j.StockExchangeBenchmark.unsafe               avgt         5      483.926       26.336    ms/op
