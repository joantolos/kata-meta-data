# Update added time metadata for videos exported from Apple's Photos app.

Whenever you export from Mac OS application Photos, the photos conserve the proper date on the metadata, but the video ones are overwritten by the exporting date. If you import the files into a new instance of Photos on other mac, or other Mac OS version, the date of the videos will be incorrect. This software fixes that date if some pre conditions are in place.

## Pre Requisits:

Use in a Unix computer with Exif tool installed. For mac:

    brew install exiftool
    
This software only works with JPG and MP4 and the file name should finish with " - 1.jpg" or " - 1.m4v". Also, the first file has to be always a JPG. 

## Usage

Place the photos and videos on the "input" folder from resources. Then execute the class "MetaDataUpdater". The new files will be stored on build/resources/main/input

## Meta data extractor    

Using this metadata extractor library for Java, by drewnoakes:

https://github.com/drewnoakes/metadata-extractor

We can extract the following metadata from the files:

Example of correct JPG file metadata:

    [JPEG] Compression Type - Progressive, Huffman
    [JPEG] Data Precision - 8 bits
    [JPEG] Image Height - 968 pixels
    [JPEG] Image Width - 544 pixels
    [JPEG] Number of Components - 3
    [JPEG] Component 1 - Y component: Quantization table 0, Sampling factors 1 horiz/1 vert
    [JPEG] Component 2 - Cb component: Quantization table 1, Sampling factors 1 horiz/1 vert
    [JPEG] Component 3 - Cr component: Quantization table 1, Sampling factors 1 horiz/1 vert
    [JFIF] Version - 1.1
    [JFIF] Resolution Units - none
    [JFIF] X Resolution - 72 dots
    [JFIF] Y Resolution - 72 dots
    [JFIF] Thumbnail Width Pixels - 0
    [JFIF] Thumbnail Height Pixels - 0
    [Exif IFD0] Orientation - Top, left side (Horizontal / normal)
    [Exif IFD0] X Resolution - 72 dots per unit
    [Exif IFD0] Y Resolution - 72 dots per unit
    [Exif IFD0] Software - Photos 4.0
    [Exif IFD0] Date/Time - 2020:01:09 21:51:40
    [Exif SubIFD] Date/Time Original - 2020:01:09 21:51:40
    [Exif SubIFD] Date/Time Digitized - 2020:01:09 21:51:40
    [Exif SubIFD] Exif Image Width - 544 pixels
    [Exif SubIFD] Exif Image Height - 968 pixels
    [XMP] XMP Value Count - 4
    [ICC Profile] Profile Size - 30240
    [ICC Profile] CMM Type - appl
    [ICC Profile] Version - 4.0.0
    [ICC Profile] Class - Input Device
    [ICC Profile] Color space - RGB
    [ICC Profile] Profile Connection Space - XYZ
    [ICC Profile] Profile Date/Time - 2016:01:01 00:00:00
    [ICC Profile] Signature - acsp
    [ICC Profile] Primary Platform - Apple Computer, Inc.
    [ICC Profile] Device manufacturer - APPL
    [ICC Profile] XYZ values - 0,964 1 0,825
    [ICC Profile] Tag Count - 8
    [ICC Profile] Profile Description - Apple Wide Color Sharing Profile
    [ICC Profile] Profile Copyright - Copyright Apple Inc., 2016
    [ICC Profile] Media White Point - (0,9642, 1, 0,8251)
    [ICC Profile] AToB 2 - mAB  (0x6D414220): 29772 bytes
    [ICC Profile] Chromatic Adaptation - sf32 (0x73663332): 44 bytes
    [ICC Profile] Unknown tag (0x61617079) - data (0x64617461): 14 bytes
    [ICC Profile] AToB 0 - mAB  (0x6D414220): 29772 bytes
    [ICC Profile] AToB 1 - mAB  (0x6D414220): 29772 bytes
    [Photoshop] Caption Digest - 212 29 140 217 143 0 178 4 233 128 9 152 236 248 66 126
    [Huffman] Number of Tables - 4 Huffman tables
    [File Type] Detected File Type Name - JPEG
    [File Type] Detected File Type Long Name - Joint Photographic Experts Group
    [File Type] Detected MIME Type - image/jpeg
    [File Type] Expected File Name Extension - jpg

Relevant metadata time to observe on a correct JPG file: [Exif IFD0] Date/Time

Example of incorrect m4v file metadata:

    [MP4] Major Brand - Apple iTunes Video (.M4V) Video
    [MP4] Minor Version - 1
    [MP4] Compatible Brands - [Apple iTunes Video (.M4V) Video, Apple iTunes AAC-LC (.M4A) Audio, MP4 v2 [ISO 14496-14], MP4  Base Media v1 [IS0 14496-12:2003]]
    [MP4] Creation Time - Fri Jan 22 19:24:17 CET 2021
    [MP4] Modification Time - Fri Jan 22 19:24:17 CET 2021
    [MP4] Duration - 4364
    [MP4] Media Time Scale - 600
    [MP4] Duration in Seconds - 00:00:08
    [MP4] Transformation Matrix - 65536 0 0 0 65536 0 0 0 1073741824
    [MP4] Preferred Rate - 1
    [MP4] Preferred Volume - 1
    [MP4] Next Track ID - 3
    [MP4] Rotation - 0
    [MP4 Sound] Creation Time - vie ene 22 19:24:17 +01:00 2021
    [MP4 Sound] Modification Time - vie ene 22 19:24:17 +01:00 2021
    [MP4 Sound] ISO 639-2 Language Code - und
    [MP4 Sound] Balance - 0
    [MP4 Sound] Format - MPEG-4, Advanced Audio Coding (AAC)
    [MP4 Sound] Number of Channels - 2
    [MP4 Sound] Sample Size - 16
    [MP4 Sound] Sample Rate - 44100
    [MP4 Video] Creation Time - vie ene 22 19:24:17 +01:00 2021
    [MP4 Video] Modification Time - vie ene 22 19:24:17 +01:00 2021
    [MP4 Video] ISO 639-2 Language Code - und
    [MP4 Video] Opcolor - 0 0 0
    [MP4 Video] Graphics Mode - Copy
    [MP4 Video] Compression Type - H.264
    [MP4 Video] Width - 848 pixels
    [MP4 Video] Height - 480 pixels
    [MP4 Video] Depth - 24-bit color
    [MP4 Video] Horizontal Resolution - 72
    [MP4 Video] Vertical Resolution - 72
    [MP4 Video] Frame Rate - 30,075
    [File Type] Detected File Type Name - MP4
    [File Type] Detected File Type Long Name - MPEG-4 Part 14
    [File Type] Detected MIME Type - video/mp4
    [File Type] Expected File Name Extension - mp4

If the metadata tag: "[MP4] Major Brand - Apple iTunes Video (.M4V) Video" exists, then modify the video date with the last available date plus one minute.