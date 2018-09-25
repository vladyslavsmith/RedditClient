package vladyslav.lubenets.domain.entity;

import java.util.List;

/**
 * Created by Lubenets Vladyslav on 9/23/18.
 */
public class TopItemModel {
    private String title;
    private String thumbnail;
    private Preview preview;
    private String author;
    private int numComments;
    private long createdUtc;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Preview getPreview() {
        return preview;
    }

    public void setPreview(Preview preview) {
        this.preview = preview;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }

    public long getCreatedUtc() {
        return createdUtc;
    }

    public void setCreatedUtc(long createdUtc) {
        this.createdUtc = createdUtc;
    }

    public static class Preview {
        private List<Images> images;

        public List<Images> getImages() {
            return images;
        }

        public void setImages(List<Images> images) {
            this.images = images;
        }

        public static class Images {
            private Source source;

            public Source getSource() {
                return source;
            }

            public void setSource(Source source) {
                this.source = source;
            }

            public static class Source {
                private String url;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}