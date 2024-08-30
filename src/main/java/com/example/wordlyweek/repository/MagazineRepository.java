/*
 * You can use the following import statements
 *
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.wordlyweek.repository;

import java.util.*;
import com.example.wordlyweek.model.*;

public interface MagazineRepository {

    ArrayList<Magazine> getAllMagazines();

    Magazine getMagazineById(int magazineId);

    Magazine addMagazine(Magazine magazine);

    Magazine updatMagazine(int magazineId, Magazine magazine);

    List<Writer> getWritersByMagazineId(int magazineId);

    void deleteMagazine(int magazineId);
}