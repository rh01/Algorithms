/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public interface Subject {
    public void registerObserver();

    public void removeObserver();

    public void notifyObserver();

}
