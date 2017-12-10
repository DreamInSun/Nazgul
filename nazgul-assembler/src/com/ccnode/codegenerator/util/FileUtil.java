// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   FileUtil.java

package com.ccnode.codegenerator.util;

import com.google.common.base.Charsets;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.*;

public class FileUtil
{

	public FileUtil()
	{
	}

	public static Iterable readFile(String filename)
		throws IOException
	{
		return readFile(new File(filename));
	}

	public static Iterable readFile(File file)
		throws IOException
	{
		LineIterator lineIterator = FileUtils.lineIterator(file, Charsets.UTF_8.name());
		return new Iterable(lineIterator) {

			final LineIterator val$lineIterator;

			public Iterator iterator()
			{
				return new Iterator() {

					final 1 this$0;

					public boolean hasNext()
					{
						boolean hasNext = lineIterator.hasNext();
						if (!hasNext)
							LineIterator.closeQuietly(lineIterator);
						return hasNext;
					}

					public String next()
					{
						if (!hasNext())
							throw new NoSuchElementException("No more lines");
						else
							return lineIterator.next();
					}

					public void remove()
					{
						throw new UnsupportedOperationException();
					}

					public volatile Object next()
					{
						return next();
					}

					
					{
						this.this$0 = 1.this;
						super();
					}
				};
			}

			
			{
				lineIterator = lineiterator;
				super();
			}
		};
	}

	public static File zip(File file)
		throws Exception
	{
		String zipFilePath;
		FileOutputStream fos;
		Exception exception;
		BufferedInputStream bis = null;
		zipFilePath = (new StringBuilder()).append(file.getPath()).append(".zip").toString();
		fos = null;
		ZipOutputStream out = null;
		byte data[] = new byte[2048];
		try
		{
			fos = new FileOutputStream(zipFilePath);
			out = new ZipOutputStream(new BufferedOutputStream(fos));
			FileInputStream fi = new FileInputStream(file);
			bis = new BufferedInputStream(fi, 2048);
			ZipEntry entry = new ZipEntry(file.getName());
			out.putNextEntry(entry);
			int count;
			while ((count = bis.read(data, 0, 2048)) != -1) 
				out.write(data, 0, count);
			bis.close();
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			IOUtils.closeQuietly(out);
		}
		IOUtils.closeQuietly(out);
		IOUtils.closeQuietly(fos);
		if (file != null && file.exists())
			file.delete();
		break MISSING_BLOCK_LABEL_203;
		IOUtils.closeQuietly(fos);
		if (file != null && file.exists())
			file.delete();
		throw exception;
		return new File(zipFilePath);
	}
}
