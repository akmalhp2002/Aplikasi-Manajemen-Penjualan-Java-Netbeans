package admin;



import admin.config;
import static com.lowagie.text.SpecialSymbol.index;
import java.awt.Color;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author ASUS
 */
public class menu extends javax.swing.JFrame {

    /**
     * Creates new form menu
     */
    public menu() {
        initComponents();
        load_table();
        load_table_plg();
        kosong();
        kosongplg();
        kosongfaktur();
        kosongkwitansi();
        kosong_table_detail_faktur();
        AutoCompleteDecorator.decorate(tb_nama_pelanggan); //untuk search combo box
        AutoCompleteDecorator.decorate(tb_nomor_faktur); //untuk search combo box
        AutoCompleteDecorator.decorate(dd_nomor_faktur); //untuk search combo box
        AutoCompleteDecorator.decorate(dd_kode_barang); //untuk search combo box
        load_combo_plg(); //load kode_plg
        load_table_faktur();
        load_combo_faktur();//load nomor_faktur
        load_table_kwitansi();
        load_combo_faktur_detail();
        load_combo_kode_barang();
        load_table_detail_faktur();
        setExtendedState(menu.MAXIMIZED_BOTH);
        Home.setVisible(true);
        Barang.setVisible(false);
        Pelanggan.setVisible(false);
        Faktur.setVisible(false);
        Kwitansi.setVisible(false);
        Detail_Faktur.setVisible(false);
        
        menuHome.setForeground(new Color(0, 51, 255));
        menuBarang.setForeground(Color.BLACK);
        menuPelanggan.setForeground(Color.BLACK);
        menuFaktur.setForeground(Color.BLACK);
        menuKwitansi.setForeground(Color.BLACK);
        menu_Detail_Faktur.setForeground(Color.BLACK);
        menuLogout.setForeground(Color.BLACK);
                
        java.awt.Color transparentColor = new java.awt.Color(0, 0, 0, 0);
        txt_kodebarang.setBackground(transparentColor);
        txt_namabarang.setBackground(transparentColor);
        txt_stokbarang.setBackground(transparentColor);
        txt_hargabarang.setBackground(transparentColor);
        txt_kodepelanggan.setBackground(transparentColor);
        txt_namapelanggan.setBackground(transparentColor);
        txt_alamatpelanggan.setBackground(transparentColor);
        txt_nomorpelanggan.setBackground(transparentColor);
        txt_nomorfaktur.setBackground(transparentColor);
        txt_nomorkwitansi.setBackground(transparentColor);
        
        txt_kodefaktur.setBackground(transparentColor);
        txt_quantity.setBackground(transparentColor);
        txt_harga_barang.setBackground(transparentColor);
    }
    
        private void kosong(){
        txt_kodebarang.setText(null);
        txt_namabarang.setText(null);
        txt_stokbarang.setText(null);  
        txt_hargabarang.setText(null);
    }
        
        private void kosongplg(){
        txt_kodepelanggan.setText(null);
        txt_namapelanggan.setText(null);
        txt_alamatpelanggan.setText(null);
        txt_nomorpelanggan.setText(null); 
        jk.setSelectedItem(this);
    }
        
        private void kosongfaktur(){
        txt_nomorfaktur.setText(null);
        JdateChooser.setDate(null);
        //tb_nama_pelanggan.setSelectedItem(null);
    }
        private void kosongkwitansi(){
        txt_nomorkwitansi.setText(null);
        JdateChooser1.setDate(null);
        //tb_nama_pelanggan.setSelectedItem(null);
    }
    
    private void load_table(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Stok Barang");
        model.addColumn("Harga Barang");
        
        //menampilkan data database kedalam tabel
        try {
            int no=1;
            String sql = "select * from barang";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{no++,res.getString(1),res.getString(2),res.getString(3),res.getString(4)});
            }
            jTable2.setModel(model);
        } catch (Exception e) {
        }
        
    }
    
    
    
    private void load_table_plg(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Kode Pelanggan");
        model.addColumn("Nama Pelanggan");
        model.addColumn("Alamat Pelanggan");
        model.addColumn("Nomor Hp");
        model.addColumn("Jenis Kelamin");
        
        //menampilkan data database kedalam tabel
        try {
            int no=1;
            String sql = "select * from pelanggan";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{no++,res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5)});
            }
            jTable3.setModel(model);
        } catch (Exception e) {
        }
        
    }
    
    //untuk load data kode plg ke combo box
    public void load_combo_plg()
    {
        try{
            String sql = "select * from pelanggan";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                tb_nama_pelanggan.addItem(res.getString("kode_plg"));
            }
            res.close();
        }
        catch (Exception e){
            
        }
    }
    
    //untuk load data nama plg ke combo box
    public void load_combo_nama_plg()
    {
        try{
            String sql = "select nama_plg from pelanggan where kode_plg='"+tb_nama_pelanggan.getSelectedItem()+"'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                tb_nama_pelanggan2.setText(res.getString("nama_plg"));
            }
            res.close();
        }
        catch (Exception e){
                
        }
    }
    
    private void load_table_faktur(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nomor Faktur");
        model.addColumn("Tanggal Faktur");
        model.addColumn("Nama Pelanggan");
        
        //menampilkan data database kedalam tabel
        try {
            int no=1;
            String sql = "select * from faktur";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{no++,res.getString(1),res.getString(2),res.getString(3)});
            }
            jTable4.setModel(model);
        } catch (Exception e) {
        }
    }
    
        //untuk load data nomor faktur ke combo box
    public void load_combo_faktur()
    {
        try{
            String sql = "select * from faktur";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                tb_nomor_faktur.addItem(res.getString("nomor_faktur"));
            }
            res.close();
        }
        catch (Exception e){
            
        }
    }
    
    public void load_combo_tgl_faktur()
    {
        try{
            String sql = "select tgl_faktur from faktur where nomor_faktur='"+tb_nomor_faktur.getSelectedItem()+"'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                tb_nomor_faktur2.setText(res.getString("tgl_faktur"));
            }
            res.close();
        }
        catch (Exception e){
                
        }
    }
    
        public void load_combo_nama_faktur()
    {
        try{
            String sql = "select faktur.*, pelanggan.* from faktur,pelanggan where faktur.kode_plg=pelanggan.kode_plg AND nomor_faktur='"+tb_nomor_faktur.getSelectedItem()+"'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                tb_nomor_faktur3.setText(res.getString("nama_plg"));
            }
            res.close();
        }
        catch (Exception e){
                
        }
    }
        
            private void load_table_kwitansi(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nomor Kwitansi");
        model.addColumn("Tanggal Kwitansi");
        model.addColumn("Nomor Faktur");
        
        //menampilkan data database kedalam tabel
        try {
            int no=1;
            String sql = "select * from kwitansi";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{no++,res.getString(1),res.getString(2),res.getString(3)});
            }
            jTable5.setModel(model);
        } catch (Exception e) {
        }
    }
            
               //untuk load data nomor faktur ke combo box
    public void load_combo_faktur_detail()
    {
        try{
            String sql = "select * from faktur";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                dd_nomor_faktur.addItem(res.getString("nomor_faktur"));
            }
            res.close();
        }
        catch (Exception e){
            
        }
    }
    
    public void load_combo_tgl_faktur_detail()
    {
        try{
            String sql = "select tgl_faktur from faktur where nomor_faktur='"+dd_nomor_faktur.getSelectedItem()+"'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                dd_nomor_faktur2.setText(res.getString("tgl_faktur"));
            }
            res.close();
        }
        catch (Exception e){
                
        }
    }
    
               //untuk load data nomor faktur ke combo box
    public void load_combo_kode_barang()
    {
        try{
            String sql = "select * from barang";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                dd_kode_barang.addItem(res.getString("kode_brg"));
            }
            res.close();
        }
        catch (Exception e){
            
        }
    }
    
    public void load_combo_nama_barang()
    {
        try{
            String sql = "select nama_brg from barang where kode_brg='"+dd_kode_barang.getSelectedItem()+"'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                dd_kode_barang2.setText(res.getString("nama_brg"));
            }
            res.close();
        }
        catch (Exception e){
                
        }
    }
    
    public void total_harga()
    {
        try {
    String qtyText = txt_quantity.getText();

    if (!qtyText.isEmpty()) {
        int qty = Integer.parseInt(qtyText);

        String sql = "SELECT harga_brg FROM barang WHERE kode_brg='" + dd_kode_barang.getSelectedItem() + "'";
        java.sql.Connection conn = (Connection) config.configDB();
        java.sql.Statement stm = conn.createStatement();
        java.sql.ResultSet res = stm.executeQuery(sql);

        while (res.next()) {
            int harga_brg = res.getInt("harga_brg");
            int c = qty * harga_brg;
            String total = String.valueOf(c);
            txt_harga_barang.setText(total);
        }
    } else {
        // Handle jika qtyText kosong, misalnya memberi nilai default atau menampilkan pesan kesalahan
        System.out.println("Error: Quantity is empty. Please enter a valid quantity.");
        // Atau bisa juga menetapkan nilai default atau menampilkan pesan kepada pengguna
    }
} catch (NumberFormatException e) {
    // Handle kesalahan konversi string ke integer
    System.out.println("Error: Invalid quantity format. Please enter a valid integer quantity.");
    // Atau bisa juga menetapkan nilai default atau menampilkan pesan kepada pengguna
} catch (Exception e) {
    // Handle kesalahan SQL
    e.printStackTrace(); // Tampilkan jejak kesalahan untuk debug
    // Atau bisa juga menetapkan nilai default atau menampilkan pesan kepada pengguna
}

    }
    
    
    private void load_table_detail_faktur(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Kode Faktur");
        model.addColumn("Nomor Faktur");
        model.addColumn("Kode Barang");
        model.addColumn("Quantity");
        model.addColumn("Total Harga");
        
        //menampilkan data database kedalam tabel
        try {
            int no=1;
            String sql = "select * from faktur_detail";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{no++,res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5)});
            }
            jTable6.setModel(model);
        } catch (Exception e) {
        }
    }
    
    private void kosong_table_detail_faktur(){
        txt_kodefaktur.setText(null);
        txt_quantity.setText(null);
    }
        
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        menuHome = new javax.swing.JLabel();
        menuBarang = new javax.swing.JLabel();
        menuPelanggan = new javax.swing.JLabel();
        menuFaktur = new javax.swing.JLabel();
        menuKwitansi = new javax.swing.JLabel();
        menu_Detail_Faktur = new javax.swing.JLabel();
        menuLogout = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Home = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Barang = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_kodebarang = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_namabarang = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_stokbarang = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_hargabarang = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        Pelanggan = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_kodepelanggan = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_namapelanggan = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txt_alamatpelanggan = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_nomorpelanggan = new javax.swing.JTextField();
        tambah_plg = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        edit_plg = new javax.swing.JButton();
        hapus_plg = new javax.swing.JButton();
        clear_plg1 = new javax.swing.JButton();
        clear_plg = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jk = new javax.swing.JComboBox<>();
        Faktur = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txt_nomorfaktur = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        tambah_faktur = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        edit_faktur = new javax.swing.JButton();
        hapus_faktur = new javax.swing.JButton();
        print_faktur = new javax.swing.JButton();
        clear_faktur = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        JdateChooser = new com.toedter.calendar.JDateChooser();
        tb_nama_pelanggan = new javax.swing.JComboBox<>();
        tb_nama_pelanggan2 = new javax.swing.JTextField();
        Kwitansi = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        txt_nomorkwitansi = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        tambah_kwitansi = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        edit_kwitansi = new javax.swing.JButton();
        hapus_kwitansi = new javax.swing.JButton();
        print_kwitansi = new javax.swing.JButton();
        clear_kwitansi = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        JdateChooser1 = new com.toedter.calendar.JDateChooser();
        tb_nomor_faktur = new javax.swing.JComboBox<>();
        tb_nomor_faktur2 = new javax.swing.JTextField();
        tb_nomor_faktur3 = new javax.swing.JTextField();
        Detail_Faktur = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txt_kodefaktur = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        tambah_faktur1 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        edit_detail_faktur = new javax.swing.JButton();
        print_detail_faktur = new javax.swing.JButton();
        hapus_faktur1 = new javax.swing.JButton();
        clear_faktur1 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        dd_kode_barang = new javax.swing.JComboBox<>();
        dd_kode_barang2 = new javax.swing.JTextField();
        dd_nomor_faktur = new javax.swing.JComboBox<>();
        dd_nomor_faktur2 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txt_quantity = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txt_harga_barang = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        menuHome.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        menuHome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuHome.setText("Home");
        menuHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuHomeMouseClicked(evt);
            }
        });

        menuBarang.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        menuBarang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuBarang.setText("Barang");
        menuBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuBarangMouseClicked(evt);
            }
        });

        menuPelanggan.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        menuPelanggan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuPelanggan.setText("Pelanggan");
        menuPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPelangganMouseClicked(evt);
            }
        });

        menuFaktur.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        menuFaktur.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuFaktur.setText("Faktur");
        menuFaktur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuFakturMouseClicked(evt);
            }
        });

        menuKwitansi.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        menuKwitansi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuKwitansi.setText("Kwitansi");
        menuKwitansi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuKwitansiMouseClicked(evt);
            }
        });

        menu_Detail_Faktur.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        menu_Detail_Faktur.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu_Detail_Faktur.setText("Detail Faktur");
        menu_Detail_Faktur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_Detail_FakturMouseClicked(evt);
            }
        });

        menuLogout.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        menuLogout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuLogout.setText("Log Out");
        menuLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLogoutMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuFaktur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuKwitansi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menu_Detail_Faktur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(menuLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(menuHome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(menuBarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(menuPelanggan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(menuFaktur)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(menuKwitansi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(menu_Detail_Faktur)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(menuLogout)
                .addContainerGap(411, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 770));

        jPanel2.setBackground(new java.awt.Color(0, 51, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Home.setBackground(new java.awt.Color(0, 51, 255));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Aplikasi Manajemen Penjualan");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bg-login.png"))); // NOI18N
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout HomeLayout = new javax.swing.GroupLayout(Home);
        Home.setLayout(HomeLayout);
        HomeLayout.setHorizontalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeLayout.createSequentialGroup()
                .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HomeLayout.createSequentialGroup()
                        .addGap(380, 380, 380)
                        .addComponent(jLabel2))
                    .addGroup(HomeLayout.createSequentialGroup()
                        .addGap(361, 361, 361)
                        .addComponent(jLabel1)))
                .addContainerGap(406, Short.MAX_VALUE))
        );
        HomeLayout.setVerticalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HomeLayout.createSequentialGroup()
                .addContainerGap(167, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(273, 273, 273))
        );

        jPanel2.add(Home, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Barang.setBackground(new java.awt.Color(0, 51, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Kode Barang");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("_________________________________________");

        txt_kodebarang.setFont(txt_kodebarang.getFont().deriveFont(txt_kodebarang.getFont().getSize()+2f));
        txt_kodebarang.setForeground(new java.awt.Color(255, 255, 255));
        txt_kodebarang.setBorder(null);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nama Barang");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("_________________________________________");

        txt_namabarang.setFont(txt_namabarang.getFont().deriveFont(txt_namabarang.getFont().getSize()+2f));
        txt_namabarang.setForeground(new java.awt.Color(255, 255, 255));
        txt_namabarang.setBorder(null);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Stock Barang");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("_________________________________________");

        txt_stokbarang.setFont(txt_stokbarang.getFont().deriveFont(txt_stokbarang.getFont().getSize()+2f));
        txt_stokbarang.setForeground(new java.awt.Color(255, 255, 255));
        txt_stokbarang.setBorder(null);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Harga Barang");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("_________________________________________");

        txt_hargabarang.setFont(txt_hargabarang.getFont().deriveFont(txt_hargabarang.getFont().getSize()+2f));
        txt_hargabarang.setForeground(new java.awt.Color(255, 255, 255));
        txt_hargabarang.setBorder(null);

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 51, 255));
        jButton4.setText("Tambah");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 51, 255));
        jButton5.setText("Edit");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 51, 255));
        jButton6.setText("Hapus");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 51, 255));
        jButton7.setText("Clear");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("MANAJEMEN BARANG");

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(0, 51, 255));
        jButton8.setText("Print");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BarangLayout = new javax.swing.GroupLayout(Barang);
        Barang.setLayout(BarangLayout);
        BarangLayout.setHorizontalGroup(
            BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarangLayout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addGroup(BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BarangLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 803, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BarangLayout.createSequentialGroup()
                        .addGroup(BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(BarangLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_hargabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_stokbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_namabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_kodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(BarangLayout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(BarangLayout.createSequentialGroup()
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(152, 152, 152))
        );
        BarangLayout.setVerticalGroup(
            BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarangLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BarangLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel5)
                        .addGap(2, 2, 2)
                        .addGroup(BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_kodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(2, 2, 2)
                        .addGroup(BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_namabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BarangLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BarangLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(2, 2, 2)
                        .addGroup(BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_stokbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(2, 2, 2)
                .addGroup(BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_hargabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(Barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Pelanggan.setBackground(new java.awt.Color(0, 51, 255));
        Pelanggan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Kode Pelanggan");
        Pelanggan.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 131, 341, -1));

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("_________________________________________");
        Pelanggan.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 151, 290, 39));

        txt_kodepelanggan.setFont(txt_kodepelanggan.getFont().deriveFont(txt_kodepelanggan.getFont().getSize()+2f));
        txt_kodepelanggan.setForeground(new java.awt.Color(255, 255, 255));
        txt_kodepelanggan.setBorder(null);
        Pelanggan.add(txt_kodepelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 151, 240, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Nama Pelanggan");
        Pelanggan.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 208, 341, -1));

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("_________________________________________");
        Pelanggan.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 228, 290, 39));

        txt_namapelanggan.setFont(txt_namapelanggan.getFont().deriveFont(txt_namapelanggan.getFont().getSize()+2f));
        txt_namapelanggan.setForeground(new java.awt.Color(255, 255, 255));
        txt_namapelanggan.setBorder(null);
        Pelanggan.add(txt_namapelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 228, 240, 30));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Alamat Pelanggan");
        Pelanggan.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 285, 341, -1));

        txt_alamatpelanggan.setFont(txt_alamatpelanggan.getFont().deriveFont(txt_alamatpelanggan.getFont().getSize()+2f));
        txt_alamatpelanggan.setForeground(new java.awt.Color(255, 255, 255));
        txt_alamatpelanggan.setBorder(null);
        Pelanggan.add(txt_alamatpelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 240, 30));

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("_________________________________________");
        Pelanggan.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 305, 290, 39));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Nomor HP Pelanggan");
        Pelanggan.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 362, 341, -1));

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("_________________________________________");
        Pelanggan.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 382, 290, 39));

        txt_nomorpelanggan.setFont(txt_nomorpelanggan.getFont().deriveFont(txt_nomorpelanggan.getFont().getSize()+2f));
        txt_nomorpelanggan.setForeground(new java.awt.Color(255, 255, 255));
        txt_nomorpelanggan.setBorder(null);
        Pelanggan.add(txt_nomorpelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 382, 240, 30));

        tambah_plg.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tambah_plg.setForeground(new java.awt.Color(0, 51, 255));
        tambah_plg.setText("Tambah");
        tambah_plg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah_plgActionPerformed(evt);
            }
        });
        Pelanggan.add(tambah_plg, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 510, 106, 34));

        jTable3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        Pelanggan.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 690, 190));

        edit_plg.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        edit_plg.setForeground(new java.awt.Color(0, 51, 255));
        edit_plg.setText("Edit");
        edit_plg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_plgActionPerformed(evt);
            }
        });
        Pelanggan.add(edit_plg, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 510, 106, 34));

        hapus_plg.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        hapus_plg.setForeground(new java.awt.Color(0, 51, 255));
        hapus_plg.setText("Hapus");
        hapus_plg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapus_plgActionPerformed(evt);
            }
        });
        Pelanggan.add(hapus_plg, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 580, 106, 34));

        clear_plg1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clear_plg1.setForeground(new java.awt.Color(0, 51, 255));
        clear_plg1.setText("Print");
        clear_plg1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_plg1ActionPerformed(evt);
            }
        });
        Pelanggan.add(clear_plg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 330, 106, 34));

        clear_plg.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clear_plg.setForeground(new java.awt.Color(0, 51, 255));
        clear_plg.setText("Clear");
        clear_plg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_plgActionPerformed(evt);
            }
        });
        Pelanggan.add(clear_plg, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 580, 106, 34));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("MANAJEMEN PELANGGAN");
        Pelanggan.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 38, -1, 45));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Jenis Kelamin");
        Pelanggan.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 430, 341, -1));

        jk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-Laki", "Perempuan" }));
        Pelanggan.add(jk, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 452, 190, 30));

        jPanel2.add(Pelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Faktur.setBackground(new java.awt.Color(0, 51, 255));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Nomor Faktur");

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("_________________________________________");

        txt_nomorfaktur.setFont(txt_nomorfaktur.getFont().deriveFont(txt_nomorfaktur.getFont().getSize()+2f));
        txt_nomorfaktur.setForeground(new java.awt.Color(255, 255, 255));
        txt_nomorfaktur.setBorder(null);
        txt_nomorfaktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nomorfakturActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Tanggal Faktur");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Nama Pelanggan");

        tambah_faktur.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tambah_faktur.setForeground(new java.awt.Color(0, 51, 255));
        tambah_faktur.setText("Tambah");
        tambah_faktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah_fakturActionPerformed(evt);
            }
        });

        jTable4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        edit_faktur.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        edit_faktur.setForeground(new java.awt.Color(0, 51, 255));
        edit_faktur.setText("Edit");
        edit_faktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_fakturActionPerformed(evt);
            }
        });

        hapus_faktur.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        hapus_faktur.setForeground(new java.awt.Color(0, 51, 255));
        hapus_faktur.setText("Hapus");
        hapus_faktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapus_fakturActionPerformed(evt);
            }
        });

        print_faktur.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        print_faktur.setForeground(new java.awt.Color(0, 51, 255));
        print_faktur.setText("Print");
        print_faktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_fakturActionPerformed(evt);
            }
        });

        clear_faktur.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clear_faktur.setForeground(new java.awt.Color(0, 51, 255));
        clear_faktur.setText("Clear");
        clear_faktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_fakturActionPerformed(evt);
            }
        });

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("MANAJEMEN FAKTUR");

        tb_nama_pelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_nama_pelangganMouseClicked(evt);
            }
        });
        tb_nama_pelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_nama_pelangganActionPerformed(evt);
            }
        });

        tb_nama_pelanggan2.setEditable(false);

        javax.swing.GroupLayout FakturLayout = new javax.swing.GroupLayout(Faktur);
        Faktur.setLayout(FakturLayout);
        FakturLayout.setHorizontalGroup(
            FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FakturLayout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addGroup(FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(FakturLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txt_nomorfaktur, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(FakturLayout.createSequentialGroup()
                        .addComponent(tambah_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(edit_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(FakturLayout.createSequentialGroup()
                        .addComponent(hapus_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(clear_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JdateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tb_nama_pelanggan2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tb_nama_pelanggan, javax.swing.GroupLayout.Alignment.LEADING, 0, 274, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(print_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(152, 152, 152))
        );
        FakturLayout.setVerticalGroup(
            FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FakturLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FakturLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel22)
                        .addGap(2, 2, 2)
                        .addGroup(FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_nomorfaktur, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JdateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FakturLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FakturLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tb_nama_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(print_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tb_nama_pelanggan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addGroup(FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambah_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hapus_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clear_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(Faktur, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Kwitansi.setBackground(new java.awt.Color(0, 51, 255));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Nomor Kwitansi");

        txt_nomorkwitansi.setFont(txt_nomorkwitansi.getFont().deriveFont(txt_nomorkwitansi.getFont().getSize()+2f));
        txt_nomorkwitansi.setForeground(new java.awt.Color(255, 255, 255));
        txt_nomorkwitansi.setBorder(null);
        txt_nomorkwitansi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nomorkwitansiActionPerformed(evt);
            }
        });

        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("_________________________________________");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Tanggal Kwitansi");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Nomor Faktur");

        tambah_kwitansi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tambah_kwitansi.setForeground(new java.awt.Color(0, 51, 255));
        tambah_kwitansi.setText("Tambah");
        tambah_kwitansi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah_kwitansiActionPerformed(evt);
            }
        });

        jTable5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);

        edit_kwitansi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        edit_kwitansi.setForeground(new java.awt.Color(0, 51, 255));
        edit_kwitansi.setText("Edit");
        edit_kwitansi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_kwitansiActionPerformed(evt);
            }
        });

        hapus_kwitansi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        hapus_kwitansi.setForeground(new java.awt.Color(0, 51, 255));
        hapus_kwitansi.setText("Hapus");
        hapus_kwitansi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapus_kwitansiActionPerformed(evt);
            }
        });

        print_kwitansi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        print_kwitansi.setForeground(new java.awt.Color(0, 51, 255));
        print_kwitansi.setText("Print");
        print_kwitansi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_kwitansiActionPerformed(evt);
            }
        });

        clear_kwitansi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clear_kwitansi.setForeground(new java.awt.Color(0, 51, 255));
        clear_kwitansi.setText("Clear");
        clear_kwitansi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_kwitansiActionPerformed(evt);
            }
        });

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("MANAJEMEN KWITANSI");

        tb_nomor_faktur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_nomor_fakturMouseClicked(evt);
            }
        });
        tb_nomor_faktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_nomor_fakturActionPerformed(evt);
            }
        });

        tb_nomor_faktur2.setEditable(false);

        tb_nomor_faktur3.setEditable(false);

        javax.swing.GroupLayout KwitansiLayout = new javax.swing.GroupLayout(Kwitansi);
        Kwitansi.setLayout(KwitansiLayout);
        KwitansiLayout.setHorizontalGroup(
            KwitansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KwitansiLayout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addGroup(KwitansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(KwitansiLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txt_nomorkwitansi, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(KwitansiLayout.createSequentialGroup()
                        .addComponent(tambah_kwitansi, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(edit_kwitansi, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(KwitansiLayout.createSequentialGroup()
                        .addComponent(hapus_kwitansi, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(clear_kwitansi, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JdateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(KwitansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tb_nomor_faktur2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tb_nomor_faktur, javax.swing.GroupLayout.Alignment.LEADING, 0, 274, Short.MAX_VALUE)
                        .addComponent(tb_nomor_faktur3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(KwitansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(print_kwitansi, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(152, 152, 152))
        );
        KwitansiLayout.setVerticalGroup(
            KwitansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KwitansiLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(KwitansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(KwitansiLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel25)
                        .addGap(2, 2, 2)
                        .addGroup(KwitansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_nomorkwitansi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JdateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, KwitansiLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(KwitansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(KwitansiLayout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tb_nomor_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(print_kwitansi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tb_nomor_faktur2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tb_nomor_faktur3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(KwitansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambah_kwitansi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit_kwitansi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(KwitansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hapus_kwitansi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clear_kwitansi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(Kwitansi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Detail_Faktur.setBackground(new java.awt.Color(0, 51, 255));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Kode Faktur");

        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("_________________________________________");

        txt_kodefaktur.setFont(txt_kodefaktur.getFont().deriveFont(txt_kodefaktur.getFont().getSize()+2f));
        txt_kodefaktur.setForeground(new java.awt.Color(255, 255, 255));
        txt_kodefaktur.setBorder(null);
        txt_kodefaktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kodefakturActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Nomor Faktur");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Kode Barang");

        tambah_faktur1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tambah_faktur1.setForeground(new java.awt.Color(0, 51, 255));
        tambah_faktur1.setText("Tambah");
        tambah_faktur1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah_faktur1ActionPerformed(evt);
            }
        });

        jTable6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable6MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable6);

        edit_detail_faktur.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        edit_detail_faktur.setForeground(new java.awt.Color(0, 51, 255));
        edit_detail_faktur.setText("Edit");
        edit_detail_faktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_detail_fakturActionPerformed(evt);
            }
        });

        print_detail_faktur.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        print_detail_faktur.setForeground(new java.awt.Color(0, 51, 255));
        print_detail_faktur.setText("Print");
        print_detail_faktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_detail_fakturActionPerformed(evt);
            }
        });

        hapus_faktur1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        hapus_faktur1.setForeground(new java.awt.Color(0, 51, 255));
        hapus_faktur1.setText("Hapus");
        hapus_faktur1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapus_faktur1ActionPerformed(evt);
            }
        });

        clear_faktur1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clear_faktur1.setForeground(new java.awt.Color(0, 51, 255));
        clear_faktur1.setText("Clear");
        clear_faktur1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_faktur1ActionPerformed(evt);
            }
        });

        jLabel36.setBackground(new java.awt.Color(255, 255, 255));
        jLabel36.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("MANAJEMEN DETAIL FAKTUR");

        dd_kode_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dd_kode_barangMouseClicked(evt);
            }
        });
        dd_kode_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dd_kode_barangActionPerformed(evt);
            }
        });

        dd_kode_barang2.setEditable(false);

        dd_nomor_faktur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dd_nomor_fakturMouseClicked(evt);
            }
        });
        dd_nomor_faktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dd_nomor_fakturActionPerformed(evt);
            }
        });

        dd_nomor_faktur2.setEditable(false);

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Quantity");

        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("_________________________________________");
        jLabel38.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jLabel38KeyTyped(evt);
            }
        });

        txt_quantity.setFont(txt_quantity.getFont().deriveFont(txt_quantity.getFont().getSize()+2f));
        txt_quantity.setForeground(new java.awt.Color(255, 255, 255));
        txt_quantity.setBorder(null);
        txt_quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_quantityActionPerformed(evt);
            }
        });
        txt_quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_quantityKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_quantityKeyTyped(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Harga Barang");

        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("_________________________________________");

        txt_harga_barang.setEditable(false);
        txt_harga_barang.setFont(txt_harga_barang.getFont().deriveFont(txt_harga_barang.getFont().getSize()+2f));
        txt_harga_barang.setForeground(new java.awt.Color(255, 255, 255));
        txt_harga_barang.setBorder(null);
        txt_harga_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_harga_barangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Detail_FakturLayout = new javax.swing.GroupLayout(Detail_Faktur);
        Detail_Faktur.setLayout(Detail_FakturLayout);
        Detail_FakturLayout.setHorizontalGroup(
            Detail_FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Detail_FakturLayout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addGroup(Detail_FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Detail_FakturLayout.createSequentialGroup()
                        .addGroup(Detail_FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Detail_FakturLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txt_kodefaktur, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Detail_FakturLayout.createSequentialGroup()
                                .addComponent(tambah_faktur1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(edit_detail_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Detail_FakturLayout.createSequentialGroup()
                                .addComponent(hapus_faktur1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(clear_faktur1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dd_nomor_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dd_nomor_faktur2, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Detail_FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(dd_kode_barang2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dd_kode_barang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(Detail_FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(print_detail_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(152, 152, 152))
                    .addGroup(Detail_FakturLayout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addContainerGap(905, Short.MAX_VALUE))
                    .addGroup(Detail_FakturLayout.createSequentialGroup()
                        .addGroup(Detail_FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Detail_FakturLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(Detail_FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_harga_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        Detail_FakturLayout.setVerticalGroup(
            Detail_FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Detail_FakturLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(Detail_FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Detail_FakturLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(print_detail_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Detail_FakturLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel32)
                        .addGap(2, 2, 2)
                        .addGroup(Detail_FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_kodefaktur, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel34)
                        .addGap(4, 4, 4)
                        .addComponent(dd_nomor_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dd_nomor_faktur2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel35)
                        .addGap(7, 7, 7)
                        .addComponent(dd_kode_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dd_kode_barang2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel37)
                .addGap(2, 2, 2)
                .addGroup(Detail_FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel39)
                .addGap(2, 2, 2)
                .addGroup(Detail_FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_harga_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(Detail_FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambah_faktur1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit_detail_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Detail_FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hapus_faktur1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clear_faktur1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(Detail_Faktur, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 1230, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuHomeMouseClicked
        // TODO add your handling code here:
        Home.setVisible(true);
        Barang.setVisible(false);
        Pelanggan.setVisible(false);
        Faktur.setVisible(false);
        Kwitansi.setVisible(false);
        menuHome.setForeground(new Color(0, 51, 255));
        menuBarang.setForeground(Color.BLACK);
        menuPelanggan.setForeground(Color.BLACK);
        menuFaktur.setForeground(Color.BLACK);
        menuKwitansi.setForeground(Color.BLACK);
        Detail_Faktur.setVisible(false);
        menu_Detail_Faktur.setForeground(Color.BLACK);
        menuLogout.setForeground(Color.BLACK);
    }//GEN-LAST:event_menuHomeMouseClicked

    private void menuBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuBarangMouseClicked
        // TODO add your handling code here:
        Barang.setVisible(true);
        Home.setVisible(false);
        Pelanggan.setVisible(false);
        Faktur.setVisible(false);
        Kwitansi.setVisible(false);
        menuBarang.setForeground(new Color(0, 51, 255));
        menuPelanggan.setForeground(Color.BLACK);
        menuFaktur.setForeground(Color.BLACK);
        menuKwitansi.setForeground(Color.BLACK);
        menuHome.setForeground(Color.BLACK);
        Detail_Faktur.setVisible(false);
        menu_Detail_Faktur.setForeground(Color.BLACK);
        menuLogout.setForeground(Color.BLACK);
    }//GEN-LAST:event_menuBarangMouseClicked

    private void menuPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPelangganMouseClicked
        // TODO add your handling code here:
        Pelanggan.setVisible(true);
        Barang.setVisible(false);
        Home.setVisible(false);
        Faktur.setVisible(false);
        Kwitansi.setVisible(false);
        menuPelanggan.setForeground(new Color(0, 51, 255));
        menuBarang.setForeground(Color.BLACK);
        menuFaktur.setForeground(Color.BLACK);
        menuKwitansi.setForeground(Color.BLACK);
        menuHome.setForeground(Color.BLACK);
        Detail_Faktur.setVisible(false);
        menu_Detail_Faktur.setForeground(Color.BLACK);
        menuLogout.setForeground(Color.BLACK);
    }//GEN-LAST:event_menuPelangganMouseClicked

    private void menuFakturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuFakturMouseClicked
        // TODO add your handling code here:
        Faktur.setVisible(true);
        Pelanggan.setVisible(false);
        Barang.setVisible(false);
        Home.setVisible(false);
        Kwitansi.setVisible(false);
        menuFaktur.setForeground(new Color(0, 51, 255));
        menuPelanggan.setForeground(Color.BLACK);
        menuBarang.setForeground(Color.BLACK);
        menuKwitansi.setForeground(Color.BLACK);
        menuHome.setForeground(Color.BLACK);
        Detail_Faktur.setVisible(false);
        menu_Detail_Faktur.setForeground(Color.BLACK);
        menuLogout.setForeground(Color.BLACK);
    }//GEN-LAST:event_menuFakturMouseClicked

    private void menuKwitansiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuKwitansiMouseClicked
        // TODO add your handling code here:
        Kwitansi.setVisible(true);
        Faktur.setVisible(false);
        Pelanggan.setVisible(false);
        Barang.setVisible(false);
        Home.setVisible(false);
        menuKwitansi.setForeground(new Color(0, 51, 255));
        menuFaktur.setForeground(Color.BLACK);
        menuPelanggan.setForeground(Color.BLACK);
        menuBarang.setForeground(Color.BLACK);
        menuHome.setForeground(Color.BLACK);
        Detail_Faktur.setVisible(false);
        menu_Detail_Faktur.setForeground(Color.BLACK);
        menuLogout.setForeground(Color.BLACK);
    }//GEN-LAST:event_menuKwitansiMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
              try {
            String sql = "INSERT INTO barang VALUES ('"+txt_kodebarang.getText()+"','"+txt_namabarang.getText()+"','"+txt_stokbarang.getText()+"','"+txt_hargabarang.getText()+"')";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
      load_table();
        kosong();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
                        try {
            String sql ="UPDATE barang SET nama_brg = '"+txt_namabarang.getText()+"', stok_brg = '"+txt_stokbarang.getText()+"', harga_brg = '"+txt_hargabarang.getText()+"' WHERE kode_brg = '"+txt_kodebarang.getText()+"'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "data berhasil di edit");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
        load_table();
        kosong();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
                try {
            String sql ="delete from barang where kode_brg='"+txt_kodebarang.getText()+"'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "berhasil di hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        load_table();
        kosong();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        kosong();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
                int baris = jTable2.rowAtPoint(evt.getPoint());
        String kode_brg =jTable2.getValueAt(baris, 1).toString();
        txt_kodebarang.setText(kode_brg);
        
        String nama_brg = jTable2.getValueAt(baris,2).toString();
        txt_namabarang.setText(nama_brg);
        
        String stok_brg = jTable2.getValueAt(baris,3).toString();
        txt_stokbarang.setText(stok_brg);
        
        String harga_brg = jTable2.getValueAt(baris,4).toString();
        txt_hargabarang.setText(harga_brg);
    }//GEN-LAST:event_jTable2MouseClicked

    private void tambah_plgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah_plgActionPerformed
        // TODO add your handling code here:
            try {
            String sql = "INSERT INTO pelanggan VALUES ('"+txt_kodepelanggan.getText()+"','"+txt_namapelanggan.getText()+"','"+txt_alamatpelanggan.getText()+"','"+txt_nomorpelanggan.getText()+"','"+jk.getSelectedItem()+"')";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        load_table_plg();
        kosongplg();
    }//GEN-LAST:event_tambah_plgActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        int baris = jTable3.rowAtPoint(evt.getPoint());
        String kode_plg =jTable3.getValueAt(baris, 1).toString();
        txt_kodepelanggan.setText(kode_plg);
        
        String nama_plg = jTable3.getValueAt(baris,2).toString();
        txt_namapelanggan.setText(nama_plg);
        
        String alamat_plg = jTable3.getValueAt(baris,3).toString();
        txt_alamatpelanggan.setText(alamat_plg);
        
        String hp_plg = jTable3.getValueAt(baris,4).toString();
        txt_nomorpelanggan.setText(hp_plg);
        
        String jenis_kelamin = jTable3.getValueAt(baris, 5).toString();
        jk.setSelectedItem(jenis_kelamin);
    }//GEN-LAST:event_jTable3MouseClicked

    private void edit_plgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_plgActionPerformed
        // TODO add your handling code here:
                try {
            String sql ="UPDATE pelanggan SET nama_plg = '"+txt_namapelanggan.getText()+"',alamat_plg= '"+txt_alamatpelanggan.getText()+"',hp_plg= '"+txt_nomorpelanggan.getText()+"', jenis_kelamin = '"+jk.getSelectedItem()+"' WHERE kode_plg = '"+txt_kodepelanggan.getText()+"'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "data berhasil di edit");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
        load_table_plg();
        kosongplg();
    }//GEN-LAST:event_edit_plgActionPerformed

    private void hapus_plgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapus_plgActionPerformed
        // TODO add your handling code here:
        try {
            String sql ="delete from pelanggan where kode_plg='"+txt_kodepelanggan.getText()+"'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "berhasil di hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        load_table_plg();
        kosongplg();
    }//GEN-LAST:event_hapus_plgActionPerformed

    private void clear_plgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_plgActionPerformed
        // TODO add your handling code here:
        kosongplg();
    }//GEN-LAST:event_clear_plgActionPerformed

    private void tambah_fakturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah_fakturActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "INSERT INTO faktur VALUES ('"+txt_nomorfaktur.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(JdateChooser.getDate())+"','"+tb_nama_pelanggan.getSelectedItem()+"')";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        kosongfaktur();
        load_table_faktur();
    }//GEN-LAST:event_tambah_fakturActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:
        // menampilkan data kedalam form pengisian:
        int baris = jTable4.rowAtPoint(evt.getPoint());
        String nomor_faktur =jTable4.getValueAt(baris, 1).toString();
        txt_nomorfaktur.setText(nomor_faktur);
        
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) jTable4.getValueAt(baris, 2));
            JdateChooser.setDate(date);
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception as needed (e.g., log it)
            // Provide a default date or take appropriate action when parsing fails
        }
    
        String kode_plg = jTable4.getValueAt(baris, 3).toString();
        tb_nama_pelanggan.setSelectedItem(kode_plg);
    }//GEN-LAST:event_jTable4MouseClicked

    private void edit_fakturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_fakturActionPerformed
        // TODO add your handling code here:
                try {
            String sql ="UPDATE faktur SET nomor_faktur = '"+txt_nomorfaktur.getText()+"', tgl_faktur = '"+new SimpleDateFormat("yyyy-MM-dd").format(JdateChooser.getDate())+"', kode_plg = '"+tb_nama_pelanggan.getSelectedItem()+"' WHERE nomor_faktur = '"+txt_nomorfaktur.getText()+"'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "data berhasil di edit");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
        kosongfaktur();
        load_table_faktur();
    }//GEN-LAST:event_edit_fakturActionPerformed

    private void hapus_fakturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapus_fakturActionPerformed
        // TODO add your handling code here:
        try {
            String sql ="delete from faktur where nomor_faktur='"+txt_nomorfaktur.getText()+"'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "berhasil di hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        kosongfaktur();
        load_table_faktur();
    }//GEN-LAST:event_hapus_fakturActionPerformed

    private void clear_fakturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_fakturActionPerformed
        // TODO add your handling code here:
        kosongfaktur();
    }//GEN-LAST:event_clear_fakturActionPerformed

    private void tb_nama_pelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_nama_pelangganMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tb_nama_pelangganMouseClicked

    private void tb_nama_pelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_nama_pelangganActionPerformed
        // TODO add your handling code here:
        load_combo_nama_plg();
    }//GEN-LAST:event_tb_nama_pelangganActionPerformed

    private void txt_nomorfakturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nomorfakturActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nomorfakturActionPerformed

    private void txt_nomorkwitansiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nomorkwitansiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nomorkwitansiActionPerformed

    private void tambah_kwitansiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah_kwitansiActionPerformed
        // TODO add your handling code here:
                try {
            String sql = "INSERT INTO kwitansi VALUES ('"+txt_nomorkwitansi.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(JdateChooser1.getDate())+"','"+tb_nomor_faktur.getSelectedItem()+"')";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
                kosongkwitansi();
                load_table_kwitansi();
    }//GEN-LAST:event_tambah_kwitansiActionPerformed

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:
        int baris = jTable5.rowAtPoint(evt.getPoint());
        String nomor_kwt =jTable5.getValueAt(baris, 1).toString();
        txt_nomorkwitansi.setText(nomor_kwt);
        
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) jTable5.getValueAt(baris, 2));
            JdateChooser1.setDate(date);
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception as needed (e.g., log it)
            // Provide a default date or take appropriate action when parsing fails
        }
    
        String kode_plg = jTable5.getValueAt(baris, 3).toString();
        tb_nomor_faktur.setSelectedItem(kode_plg);
    }//GEN-LAST:event_jTable5MouseClicked

    private void edit_kwitansiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_kwitansiActionPerformed
        // TODO add your handling code here:
        try {
            String sql ="UPDATE kwitansi SET nomor_kwt = '"+txt_nomorkwitansi.getText()+"', tgl_kwt = '"+new SimpleDateFormat("yyyy-MM-dd").format(JdateChooser1.getDate())+"', nomor_faktur = '"+tb_nomor_faktur.getSelectedItem()+"' WHERE nomor_kwt = '"+txt_nomorkwitansi.getText()+"'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "data berhasil di edit");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
        kosongkwitansi();
        load_table_kwitansi();
    }//GEN-LAST:event_edit_kwitansiActionPerformed

    private void hapus_kwitansiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapus_kwitansiActionPerformed
        // TODO add your handling code here:
        try {
            String sql ="delete from kwitansi where nomor_kwt='"+txt_nomorkwitansi.getText()+"'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "berhasil di hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        kosongkwitansi();
        load_table_kwitansi();
    }//GEN-LAST:event_hapus_kwitansiActionPerformed

    private void clear_kwitansiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_kwitansiActionPerformed
        // TODO add your handling code here:
        kosongkwitansi();
    }//GEN-LAST:event_clear_kwitansiActionPerformed

    private void tb_nomor_fakturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_nomor_fakturMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_nomor_fakturMouseClicked

    private void tb_nomor_fakturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_nomor_fakturActionPerformed
        // TODO add your handling code here:
        load_combo_tgl_faktur();
        load_combo_nama_faktur();
    }//GEN-LAST:event_tb_nomor_fakturActionPerformed

    private void txt_kodefakturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kodefakturActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kodefakturActionPerformed

    private void tambah_faktur1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah_faktur1ActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "INSERT INTO faktur_detail VALUES ('"+txt_kodefaktur.getText()+"','"+dd_nomor_faktur.getSelectedItem()+"','"+dd_kode_barang.getSelectedItem()+"','"+txt_quantity.getText()+"','"+txt_harga_barang.getText()+"')";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        load_table_detail_faktur();
        kosong_table_detail_faktur();
    }//GEN-LAST:event_tambah_faktur1ActionPerformed

    private void jTable6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MouseClicked
        // TODO add your handling code here:
                // menampilkan data kedalam form pengisian:
        int baris = jTable6.rowAtPoint(evt.getPoint());
        String kode_faktur =jTable6.getValueAt(baris, 1).toString();
        txt_kodefaktur.setText(kode_faktur);
 
        String nomor_faktur = jTable6.getValueAt(baris, 2).toString();
        dd_nomor_faktur.setSelectedItem(nomor_faktur);
        
        String kode_brg = jTable6.getValueAt(baris, 3).toString();
        dd_kode_barang.setSelectedItem(kode_brg);
        
        String qty = jTable6.getValueAt(baris, 4).toString();
        txt_quantity.setText(qty);
        
        String harga_brg = jTable6.getValueAt(baris, 5).toString();
        txt_harga_barang.setText(harga_brg);
    }//GEN-LAST:event_jTable6MouseClicked

    private void edit_detail_fakturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_detail_fakturActionPerformed
        // TODO add your handling code here:
        try {
            String sql ="UPDATE faktur_detail SET kode_faktur = '"+txt_kodefaktur.getText()+"', nomor_faktur = '"+dd_nomor_faktur.getSelectedItem()+"', kode_brg = '"+dd_kode_barang.getSelectedItem()+"',qty= '"+txt_quantity.getText()+"',harga_brg= '"+txt_harga_barang.getText()+"' WHERE kode_faktur = '"+txt_kodefaktur.getText()+"'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "data berhasil di edit");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
        load_table_detail_faktur();
        kosong_table_detail_faktur();
    }//GEN-LAST:event_edit_detail_fakturActionPerformed

    private void hapus_faktur1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapus_faktur1ActionPerformed
        // TODO add your handling code here:
         try {
            String sql ="delete from faktur_detail where kode_faktur='"+txt_kodefaktur.getText()+"'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "berhasil di hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        load_table_detail_faktur();
        kosong_table_detail_faktur();
    }//GEN-LAST:event_hapus_faktur1ActionPerformed

    private void clear_faktur1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_faktur1ActionPerformed
        // TODO add your handling code here:
        kosong_table_detail_faktur();
    }//GEN-LAST:event_clear_faktur1ActionPerformed

    private void dd_kode_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dd_kode_barangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dd_kode_barangMouseClicked

    private void dd_kode_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dd_kode_barangActionPerformed
        // TODO add your handling code here:
        load_combo_nama_barang();
    }//GEN-LAST:event_dd_kode_barangActionPerformed

    private void menu_Detail_FakturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_Detail_FakturMouseClicked
        // TODO add your handling code here:
        Detail_Faktur.setVisible(true);
        Faktur.setVisible(false);
        Pelanggan.setVisible(false);
        Barang.setVisible(false);
        Home.setVisible(false);
        Kwitansi.setVisible(false);
        menu_Detail_Faktur.setForeground(new Color(0, 51, 255));
        menuFaktur.setForeground(Color.BLACK);
        menuPelanggan.setForeground(Color.BLACK);
        menuBarang.setForeground(Color.BLACK);
        menuKwitansi.setForeground(Color.BLACK);
        menuHome.setForeground(Color.BLACK);
        menuLogout.setForeground(Color.BLACK);
    }//GEN-LAST:event_menu_Detail_FakturMouseClicked

    private void dd_nomor_fakturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dd_nomor_fakturMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dd_nomor_fakturMouseClicked

    private void dd_nomor_fakturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dd_nomor_fakturActionPerformed
        // TODO add your handling code here:
        load_combo_tgl_faktur_detail();
    }//GEN-LAST:event_dd_nomor_fakturActionPerformed

    private void txt_quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_quantityActionPerformed

    private void txt_harga_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_harga_barangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_harga_barangActionPerformed

    private void txt_quantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_quantityKeyReleased
        // TODO add your handling code here:
        total_harga();
    }//GEN-LAST:event_txt_quantityKeyReleased

    private void jLabel38KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel38KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel38KeyTyped

    private void txt_quantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_quantityKeyTyped
        // TODO add your handling code here:
        char a = evt.getKeyChar();
        
        if(!Character.isDigit(a)){
            evt.consume();
        }
    }//GEN-LAST:event_txt_quantityKeyTyped

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
          try {
        JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("barang.jasper"), null, config.configDB());
        JasperViewer.viewReport(jp, false);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void clear_plg1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_plg1ActionPerformed
        // TODO add your handling code here:
        try {
        JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("pelanggan.jasper"), null, config.configDB());
        JasperViewer.viewReport(jp, false);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_clear_plg1ActionPerformed

    private void print_fakturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_fakturActionPerformed
        // TODO add your handling code here:
        try {
        JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("faktur.jasper"), null, config.configDB());
        JasperViewer.viewReport(jp, false);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_print_fakturActionPerformed

    private void print_kwitansiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_kwitansiActionPerformed
        // TODO add your handling code here:
        try {
        JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("kwitansi.jasper"), null, config.configDB());
        JasperViewer.viewReport(jp, false);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_print_kwitansiActionPerformed

    private void print_detail_fakturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_detail_fakturActionPerformed
        // TODO add your handling code here:
        try {
        JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("detail_faktur.jasper"), null, config.configDB());
        JasperViewer.viewReport(jp, false);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_print_detail_fakturActionPerformed

    private void menuLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLogoutMouseClicked
        // TODO add your handling code here:
        loginform.login fl = new loginform.login();
        fl.show();
        this.dispose();
    }//GEN-LAST:event_menuLogoutMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Barang;
    private javax.swing.JPanel Detail_Faktur;
    private javax.swing.JPanel Faktur;
    private javax.swing.JPanel Home;
    private com.toedter.calendar.JDateChooser JdateChooser;
    private com.toedter.calendar.JDateChooser JdateChooser1;
    private javax.swing.JPanel Kwitansi;
    private javax.swing.JPanel Pelanggan;
    private javax.swing.JButton clear_faktur;
    private javax.swing.JButton clear_faktur1;
    private javax.swing.JButton clear_kwitansi;
    private javax.swing.JButton clear_plg;
    private javax.swing.JButton clear_plg1;
    private javax.swing.JComboBox<String> dd_kode_barang;
    private javax.swing.JTextField dd_kode_barang2;
    private javax.swing.JComboBox<String> dd_nomor_faktur;
    private javax.swing.JTextField dd_nomor_faktur2;
    private javax.swing.JButton edit_detail_faktur;
    private javax.swing.JButton edit_faktur;
    private javax.swing.JButton edit_kwitansi;
    private javax.swing.JButton edit_plg;
    private javax.swing.JButton hapus_faktur;
    private javax.swing.JButton hapus_faktur1;
    private javax.swing.JButton hapus_kwitansi;
    private javax.swing.JButton hapus_plg;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JComboBox<String> jk;
    private javax.swing.JLabel menuBarang;
    private javax.swing.JLabel menuFaktur;
    private javax.swing.JLabel menuHome;
    private javax.swing.JLabel menuKwitansi;
    private javax.swing.JLabel menuLogout;
    private javax.swing.JLabel menuPelanggan;
    private javax.swing.JLabel menu_Detail_Faktur;
    private javax.swing.JButton print_detail_faktur;
    private javax.swing.JButton print_faktur;
    private javax.swing.JButton print_kwitansi;
    private javax.swing.JButton tambah_faktur;
    private javax.swing.JButton tambah_faktur1;
    private javax.swing.JButton tambah_kwitansi;
    private javax.swing.JButton tambah_plg;
    private javax.swing.JComboBox<String> tb_nama_pelanggan;
    private javax.swing.JTextField tb_nama_pelanggan2;
    private javax.swing.JComboBox<String> tb_nomor_faktur;
    private javax.swing.JTextField tb_nomor_faktur2;
    private javax.swing.JTextField tb_nomor_faktur3;
    private javax.swing.JTextField txt_alamatpelanggan;
    private javax.swing.JTextField txt_harga_barang;
    private javax.swing.JTextField txt_hargabarang;
    private javax.swing.JTextField txt_kodebarang;
    private javax.swing.JTextField txt_kodefaktur;
    private javax.swing.JTextField txt_kodepelanggan;
    private javax.swing.JTextField txt_namabarang;
    private javax.swing.JTextField txt_namapelanggan;
    private javax.swing.JTextField txt_nomorfaktur;
    private javax.swing.JTextField txt_nomorkwitansi;
    private javax.swing.JTextField txt_nomorpelanggan;
    private javax.swing.JTextField txt_quantity;
    private javax.swing.JTextField txt_stokbarang;
    // End of variables declaration//GEN-END:variables
}
