package ru.tinkoff.edu.java.scrapper.service;

import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.data.request.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.data.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.data.response.LinkResponse;
import ru.tinkoff.edu.java.scrapper.exception.UserIdNotFoundException;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DumbService {

    private static final Map<Long, Set<URI>> db = new ConcurrentHashMap<>();

    public LinkResponse add(Long id, AddLinkRequest request) {
        if (!db.containsKey(id)) {
            throw new UserIdNotFoundException();
        }
        URI url = request.link();
        System.out.println(id + " - " + url + " --" + db.get(id).size());
        db.get(id).add(url);
        return new LinkResponse(id, url);
    }

    public LinkResponse remove(Long id, RemoveLinkRequest request) {
        if (!db.containsKey(id)) {
            throw new UserIdNotFoundException();
        }
        URI url = request.link();
        db.get(id).remove(url);
        return new LinkResponse(id, url);
    }

    public void addUser(Long id) {
        ConcurrentHashMap<URI, Void> tmp = new ConcurrentHashMap<>();
//        db.put(id, tmp.keySet());
        db.put(id, new HashSet<>());
    }

    public void removeUser(Long id) {
        db.remove(id);
    }

    public List<LinkResponse> getAll(Long id) {
        if (!db.containsKey(id)) {
            throw new UserIdNotFoundException();
        }
        return db.get(id).stream()
                .map(x -> new LinkResponse(id, x))
                .toList();
    }
}
