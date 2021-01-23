package com.example.postrequestserver.utill;

import lombok.*;


@RequiredArgsConstructor
public class UrlGetter {

    private static final String PROTOCOL = "http://";

    @NonNull private final String url;
    @NonNull private final String port;
    @NonNull private final String path;

    @Setter
    private Integer start;
    @Setter
    private Integer end;
    @Setter
    private Integer ap;



    public String getFullURL() {
        return PROTOCOL + url + ":" + port + "/" + path + "?offsetOption=" + start + "," + end;
    }

    public void addOffset() {
        start += ap;
        end += ap;
    }


}
