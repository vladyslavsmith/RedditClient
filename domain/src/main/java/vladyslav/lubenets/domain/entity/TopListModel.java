package vladyslav.lubenets.domain.entity;

import java.util.List;

/**
 * Created by Lubenets Vladyslav on 9/22/18.
 */
public class TopListModel {

    private ListData data;

    public ListData getData() {
        return data;
    }

    public void setData(ListData data) {
        this.data = data;
    }

    public static class ListData {
        private String after;

        private List<TopItemWrapperModel> children;

        public void setAfter(String after) {
            this.after = after;
        }

        public void setChildren(List<TopItemWrapperModel> children) {
            this.children = children;
        }

        public String getAfter() {
            return after;
        }

        public List<TopItemWrapperModel> getChildren() {
            return children;
        }

    }
}