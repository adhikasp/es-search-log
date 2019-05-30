# es-search-log

An Elastic Search plugin to log all REST request

This will create a log `es-search.log` in `{es_home}/log` with the following format

`Inbound for URI [METHOD] [URL] with content [JSON payload]`

```
Inbound for URI GET /bank/_search?q=* with content [EMPTY]
Inbound for URI GET /bank/_search?q=* with content [EMPTY]
Inbound for URI POST /bank/_search with content {"query": {"match_all": {}}, "size": "1"}
Inbound for URI POST /bank/_bulk?refresh with content {"index":{"_id":"1"}} {"account_number":1 ...OMITTED.. }
Inbound for URI GET /_cat/indices?v with content [EMPTY]
```