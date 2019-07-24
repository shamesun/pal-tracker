package io.pivotal.pal.tracker;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController
{
    TimeEntryRepository timeEntriesRepo;

    public TimeEntryController( TimeEntryRepository timeEntriesRepo) {
        this.timeEntriesRepo = timeEntriesRepo;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate ) {
        return  new ResponseEntity(timeEntriesRepo.create(timeEntryToCreate),HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id ) {
        TimeEntry entity =timeEntriesRepo.find(id);
        System.err.println("ID:"+id);
        if(entity !=null)
        {
            return new ResponseEntity<>(entity,HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<>(timeEntriesRepo.list(),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody TimeEntry timeEntry )
    {
        TimeEntry updatedEntity = timeEntriesRepo.update(id, timeEntry);

     if(updatedEntity !=null)
     {
         return new ResponseEntity(updatedEntity,HttpStatus.OK);
     }else{
         return  new ResponseEntity(HttpStatus.NOT_FOUND);
     }
        }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
     //   if(timeEntriesRepo.find(id)!=null){
        timeEntriesRepo.delete(id);
        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    /*}else{
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    */}

}
