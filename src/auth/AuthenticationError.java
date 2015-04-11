package auth;

/**
 * The <code>AuthenticationError</code> class represents an name during
 * authentication with <a href="http://twitch.tv">http://www.twitch.tv</a>.
 */
public class AuthenticationError {
    private String name;
    private String description;

    public AuthenticationError(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "AuthenticationError{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthenticationError that = (AuthenticationError) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return !(description != null ? !description.equals(that.description) : that.description != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
