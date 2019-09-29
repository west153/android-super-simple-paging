package devcrema.android_super_simple_paging.test;

public class SampleItem {
    private Long id;
    private String content;

    public SampleItem(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
