# VideoTestApp

Simple app, that loads 4 video files from the hardcoded host and shows message on error.

## Task

Сделать приложение, на главном экране которого будет pageview с 4 страницами. На каждой странице - видеоролик с возможностью его запустить. Список файлов получать с сервера из JSON по адресу https://89.208.230.255/test/item (поля: src, single, split_v, split_h, ошибку сертификата игнорировать). Файлы скачать по указанным адресам.

Обработать ошибки:
* неработающий сервер (ошибка возникнет при получении файла из поля split_v),
* отсутствующий файл (поле src).

При ошибке показывать информацию на соответствующей странице.

