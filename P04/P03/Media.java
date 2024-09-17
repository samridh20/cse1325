public class Media{

    private String title;
    private String url;


    public Media(String title,String url){

        if (title == null || title.isEmpty()){
            throw new IllegalArgumentException("Media title is empty");
        }

        if (url == null || url.isEmpty()) {

            throw new IllegalArgumentException("URL is empty");
        }

        this.title = title;
        this.url = url;
    }
    @Override
    public String toString(){

        return String.format("%s (%s)", title, url);
    }
}