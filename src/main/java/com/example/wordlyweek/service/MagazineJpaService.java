/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * 
 * import java.util.*;
 *
 */

// Write your code here
package com.example.wordlyweek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import com.example.wordlyweek.repository.*;
import com.example.wordlyweek.model.*;

@Service

public class MagazineJpaService implements MagazineRepository {
    @Autowired
    private MagazineJpaRepository magazineJpaRepository;
    @Autowired
    private WriterJpaRepository writerJpaRepository;

    @Override
    public ArrayList<Magazine> getAllMagazines() {
        List<Magazine> magazineList = magazineJpaRepository.findAll();
        ArrayList<Magazine> magazines = new ArrayList<>(magazineList);
        return magazines;

    }

    @Override
    public Magazine getMagazineById(int magazineId) {
        try {
            Magazine magazine = magazineJpaRepository.findById(magazineId).get();
            return magazine;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Magazine addMagazine(Magazine magazine) {
        List<Integer> writerIds = new ArrayList<>();
        for (Writer writer : magazine.getWriters()) {
            writerIds.add(writer.getWriterId());
        }
        try {
            List<Writer> completeWriters = writerJpaRepository.findAllById(writerIds);
            if (writerIds.size() != completeWriters.size()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            magazine.setWriters(completeWriters);
            magazineJpaRepository.save(magazine);
            return magazine;

        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public Magazine updatMagazine(int magazineId, Magazine magazine) {
        try {
            Magazine newMagazine = magazineJpaRepository.findById(magazineId).get();
            if (magazine.getMagazineName() != null) {
                newMagazine.setMagazineName(magazine.getMagazineName());
            }
            if (magazine.getPublicationDate() != null) {
                newMagazine.setPublicationDate(magazine.getPublicationDate());
            }
            if (magazine.getWriters() != null) {
                List<Integer> writerIds = new ArrayList<>();
                for (Writer writer : magazine.getWriters()) {
                    writerIds.add(writer.getWriterId());
                }
                List<Writer> completeWriters = writerJpaRepository.findAllById(writerIds);
                if (writerIds.size() != completeWriters.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }
                magazine.setWriters(completeWriters);

            }
            magazineJpaRepository.save(newMagazine);
            return newMagazine;

        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteMagazine(int magazineId) {
        try {
            Magazine magazine = magazineJpaRepository.findById(magazineId).get();
            List<Writer> writers = magazine.getWriters();
            for (Writer writer : writers) {
                writer.getMagazines().remove(magazine);
            }
            writerJpaRepository.saveAll(writers);
            magazineJpaRepository.deleteById(magazineId);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public  List<Writer> getWritersByMagazineId( int magazineId){
        try {
            Magazine magazine = magazineJpaRepository.findById(magazineId).get();
            return magazine.getWriters();
            
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}