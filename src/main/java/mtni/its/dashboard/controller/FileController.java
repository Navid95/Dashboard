package mtni.its.dashboard.controller;

//@CrossOrigin("http://localhost:8080")
//@Controller
//@RequestMapping(value = "/api/csv")
public class FileController {

//    @Autowired
//    CSVService csvService;
//    private EDW_NO_RE_SHWG_Service edw_no_re_shwg_service;
//
//    @Autowired
//    public void setEdw_no_re_shwg_service(EDW_NO_RE_SHWG_Service edw_no_re_shwg_service) {
//        this.edw_no_re_shwg_service = edw_no_re_shwg_service;
//    }
//
//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//        System.err.println("*******************************************************************************************");
//        String message = "";
//        if (CsvUtility.hasCSVFormat(file)) {
//            try {
//                csvService.save(file);
//                message = "Uploaded the file successfully: " + file.getOriginalFilename();
//                return ResponseEntity.status(HttpStatus.OK).body(message);
//            } catch (Exception e) {
//                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
//            }
//        }
//        message = "Please upload a csv file!";
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
//    }
//
//
//
//
//
//    @GetMapping("/tutorials")
//    public ResponseEntity<List<ENRSH>> getAllTutorials() {
//        System.err.println("*******************************************************************************************");
//
//        try {
//            List<ENRSH> tutorials = new ArrayList<>();
//            for (Object record : edw_no_re_shwg_service.getAllTutorials())
//            {
//                ENRSH edw_no_re_shwg = (ENRSH) record;
//                tutorials.add(edw_no_re_shwg);
//            }
//            if (tutorials.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(tutorials, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
//
//
//
//    @GetMapping("/download/{fileName:.+}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
//        System.err.println("*******************************************************************************************");
//        InputStreamResource file = new InputStreamResource(csvService.load());
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
//                .contentType(MediaType.parseMediaType("application/csv"))
//                .body(file);
//    }

}
