package vladyslav.lubenets.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lubenets Vladyslav on 9/22/18.
 */
public class TopListApiModel {

    private ListData data;

    public ListData getData() {
        return data;
    }

    public void setData(ListData data) {
        this.data = data;
    }

    public static class ListData {
        private String after;
        private List<Children> children;

        public String getAfter() {
            return after;
        }

        public List<Children> getChildren() {
            return children;
        }

        public static class Children {
            private Data data;

            public Data getData() {
                return data;
            }

            public void setData(Data data) {
                this.data = data;
            }

            public static class Data {
                private String title;
                private String thumbnail;
                private Preview preview;
                private String author;
                @SerializedName("num_comments")
                private int numComments;
                @SerializedName("created_utc")
                private double createdUtc;

                public String getTitle() {
                    return title;
                }

                public String getThumbnail() {
                    return thumbnail;
                }

                public Preview getPreview() {
                    return preview;
                }

                public String getAuthor() {
                    return author;
                }

                public int getNumComments() {
                    return numComments;
                }

                public double getCreatedUtc() {
                    return createdUtc;
                }

                public static class Preview {
                    private List<Images> images;

                    public List<Images> getImages() {
                        return images;
                    }

                    public static class Images {
                        @SerializedName("source")
                        private Source source;

                        public Source getSource() {
                            return source;
                        }

                        public static class Source {
                            @SerializedName("url")
                            private String url;

                            public String getUrl() {
                                return url;
                            }

                        }
                    }
                }
            }
        }
    }
}
